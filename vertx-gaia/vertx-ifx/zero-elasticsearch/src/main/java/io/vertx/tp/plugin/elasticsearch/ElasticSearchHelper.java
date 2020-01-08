package io.vertx.tp.plugin.elasticsearch;

import io.vertx.core.json.JsonObject;
import io.vertx.tp.error._404ConfigurationMissingExceptionn;
import io.vertx.up.fn.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.util.Ut;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Hongwei
 * @since 2019/12/29, 13:31
 */

public class ElasticSearchHelper {
	private static final Annal logger = Annal.get(ElasticSearchHelper.class);

	private transient final Class<?> target;

	private ElasticSearchHelper(final Class<?> target) {
		this.target = target;
	}

	static ElasticSearchHelper helper(final Class<?> target) {
		return Fn.pool(Pool.HELPERS, target.getName(), () -> new ElasticSearchHelper(target));
	}

	RestHighLevelClient getClient(final JsonObject options) {
		Fn.outWeb(Ut.isNil(options), _404ConfigurationMissingExceptionn.class, this.getClass());
		logger.debug("ElasticSearch Config: ", options.getString("hostname"), options.getInteger("port"), options.getString("scheme"));
		return new RestHighLevelClient(
			RestClient.builder(
				new HttpHost(options.getString("hostname"), options.getInteger("port"), options.getString("scheme"))
			)
		);
	}

	void closeClient(final RestHighLevelClient client) {
		try {
			client.close();
		} catch (IOException ioe) {
			logger.error("error occurred when close elasticsearch connection", ioe.getMessage());
		}
	}

	Settings settingsBuilder(final int numberOfShards, final int numberOfReplicas) {
		return Settings.builder()
			.put("index.number_of_shards", numberOfShards > 0 ? numberOfShards : 5)
			.put("index.number_of_replicas", numberOfReplicas > 0 ? numberOfReplicas: 3)
			.build();
	}

	/**
	 * build mappings for index from fields and fields' type
	 * @param mappings fields with type, format like below
	 * {
	 *   "field": "String",
	 *   ...
	 * }
	 * @return translated map object
	 */
	Map<String, Object> mappingsBuilder(final JsonObject mappings) {
		JsonObject result = new JsonObject();

		// process field: key
		if (mappings.containsKey("key")) {
			result.put("key", new JsonObject()
				.put("type", "keyword")
				.put("index", true)
			);
			mappings.remove("key");
		}

		// process rest fields
		mappings.forEach(item -> {
			final String key = item.getKey();
			final String value = item.getValue().toString();
			if (StringUtils.equalsIgnoreCase(value, "STRING1") ||
					StringUtils.equalsIgnoreCase(value, "STRING2") ||
					StringUtils.equalsIgnoreCase(value, "JSON") ||
					StringUtils.equalsIgnoreCase(value, "XML") ||
					StringUtils.equalsIgnoreCase(value, "SCRIPT")) {
				if (StringUtils.containsIgnoreCase(key, "ip")) {
					JsonObject props = new JsonObject()
						.put("type", "ip")
						.put("index", "true");
					result.put(key, props);
				} else {
					JsonObject props = new JsonObject()
						.put("type", "text")
						.put("index", "true")
						.put("analyzer", "ik_max_word");
					result.put(key, props);
				}
			} else if (StringUtils.equalsIgnoreCase(value, "DATE1") ||
						StringUtils.equalsIgnoreCase(value, "DATE2") ||
						StringUtils.equalsIgnoreCase(value, "DATE3") ||
						StringUtils.equalsIgnoreCase(value, "DATE4")) {
				JsonObject props = new JsonObject()
					.put("type", "date")
					.put("index", "true")
					.put("format", "yyyy-MM-dd||yyyy-MM-dd HH:mm:ss||epoch_millis");
				result.put(key, props);
			} else if (StringUtils.equalsIgnoreCase(value, "INT")) {
				JsonObject props = new JsonObject()
					.put("type", "integer")
					.put("index", "true");
				result.put(key, props);
			} else if (StringUtils.equalsIgnoreCase(value, "LONG")) {
				JsonObject props = new JsonObject()
					.put("type", "long")
					.put("index", "true");
				result.put(key, props);
			} else if (StringUtils.equalsIgnoreCase(value, "DECIMAL")) {
				JsonObject props = new JsonObject()
					.put("type", "double")
					.put("index", "true");
				result.put(key, props);
			} else if (StringUtils.equalsIgnoreCase(value, "BOOLEAN")) {
				JsonObject props = new JsonObject()
					.put("type", "boolean")
					.put("index", "true");
				result.put(key, props);
			} else if (StringUtils.equalsIgnoreCase(value, "BINARY")) {
				JsonObject props = new JsonObject()
					.put("type", "binary")
					.put("index", "true");
				result.put(key, props);
			} else {
				JsonObject props = new JsonObject()
					.put("type", "text")
					.put("index", "true")
					.put("analyzer", "ik_max_word");
				result.put(key, props);
			}
		});

		return result.getMap();
	}

	SearchSourceBuilder searchSourceBuilder(final String searchText, int from, int size) {
		return new SearchSourceBuilder()
			.query(QueryBuilders.queryStringQuery(searchText))
			.aggregation(AggregationBuilders.terms(Aggregations.AGGREGATIONS_FIELD).field("_index"))
			.highlighter(new HighlightBuilder().field("*").preTags("<strong>").postTags("</strong>").highlighterType("unified"))
			.from(Math.max(0, from))
			.size(Math.max(10, size))
			.timeout(new TimeValue(60, TimeUnit.SECONDS));
	}
}
