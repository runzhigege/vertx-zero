package io.vertx.tp.plugin.elasticsearch;

import io.vertx.core.json.JsonObject;
import io.vertx.up.fn.Fn;
import io.vertx.up.log.Annal;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import java.io.IOException;
import java.util.Map;

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

	RestHighLevelClient getClient() {
		return new RestHighLevelClient(
			RestClient.builder(
				new HttpHost("localhost", 9200, "http")
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
			.put("index.number_of_shards", numberOfShards)
			.put("index.number_of_replicas", numberOfReplicas)
			.build();
	}

	Map<String, Object> mappingsBuilder(final JsonObject mappings) {
		mappings.forEach(item -> {
			item.getKey()
		});
	}
}
