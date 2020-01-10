package io.vertx.tp.plugin.elasticsearch;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.error._404ConfigurationMissingExceptionn;
import io.vertx.tp.error._404IndexNameMissingExceptionn;
import io.vertx.tp.error._404SearchTextMissingExceptionn;
import io.vertx.up.fn.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.util.Ut;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.settings.put.UpdateSettingsRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.Aggregations;

import java.io.IOException;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

/**
 * @author Hongwei
 * @since 2019/12/29, 13:31
 */

public class ElasticSearchClientImpl implements ElasticSearchClient {
	private static final Annal logger = Annal.get(ElasticSearchClientImpl.class);

	private final transient Vertx vertx;
	private final transient JsonObject options = new JsonObject();
	private transient final ElasticSearchHelper helper = ElasticSearchHelper.helper(this.getClass());

	ElasticSearchClientImpl(final Vertx vertx, final JsonObject options) {
		this.vertx = vertx;
		if (Ut.notNil(options)) {
			this.options.mergeIn(options);
		}
	}

	@Override
	public JsonObject getIndex(String index) {
		JsonObject result = new JsonObject();
		RestHighLevelClient client = helper.getClient(options);

		try {
			GetIndexRequest request = new GetIndexRequest(index);
			request.includeDefaults(true);
			request.indicesOptions(IndicesOptions.lenientExpandOpen());

			GetIndexResponse response = client.indices().get(request, RequestOptions.DEFAULT);

			result
				.put("index", response.getIndices())
				.put("alias", response.getAliases())
				.put("defaultSettings", response.getDefaultSettings())
				.put("settings", response.getSettings())
				.put("mappings", response.getMappings());
		} catch (IOException ioe) {
			logger.debug("failed to get index information", ioe.getMessage());
		}

		helper.closeClient(client);
		return result;
	}

	@Override
	public JsonObject createIndex(String index, int numberOfShards, int numberOfReplicas, ConcurrentMap<String, Class<?>> mappings) {
		JsonObject result = new JsonObject();
		RestHighLevelClient client = helper.getClient(options);

		try {
			CreateIndexRequest request = new CreateIndexRequest(index);
			request.alias(new Alias(options.getString("index")));
			request.settings(helper.settingsBuilder(numberOfShards, numberOfReplicas));
			request.mapping(helper.mappingsBuilder(mappings));

			CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);

			result.put("isAcknowledged", response.isAcknowledged());
		} catch (IOException ioe) {
			logger.debug("failed to create index", ioe.getMessage());
		}

		helper.closeClient(client);
		return result;
	}

	@Override
	public JsonObject updateIndex(String index, int numberOfShards, int numberOfReplicas) {
		JsonObject result = new JsonObject();
		RestHighLevelClient client = helper.getClient(options);

		try {
			UpdateSettingsRequest request = new UpdateSettingsRequest(index);
			request.settings(helper.settingsBuilder(numberOfShards, numberOfReplicas));

			AcknowledgedResponse response = client.indices().putSettings(request, RequestOptions.DEFAULT);

			result.put("isAcknowledged", response.isAcknowledged());
		} catch (IOException ioe) {
			logger.debug("failed to update index settings", ioe.getMessage());
		}

		helper.closeClient(client);
		return result;
	}

	@Override
	public JsonObject deleteIndex(String index) {
		JsonObject result = new JsonObject();
		RestHighLevelClient client = helper.getClient(options);

		try {
			DeleteIndexRequest request = new DeleteIndexRequest(index);

			AcknowledgedResponse response = client.indices().delete(request, RequestOptions.DEFAULT);

			result.put("isAcknowledged", response.isAcknowledged());
		} catch (IOException ioe) {
			logger.debug("failed to delete index", ioe.getMessage());
		}

		helper.closeClient(client);
		return result;
	}

	@Override
	public JsonObject getDocument(String index, String documentId) {
		JsonObject result = new JsonObject();
		RestHighLevelClient client = helper.getClient(options);

		try {
			GetRequest request = new GetRequest()
				.index(index)
				.id(documentId);

			GetResponse response = client.get(request, RequestOptions.DEFAULT);

			result
				.put("index", response.getIndex())
				.put("id", response.getId())
				.put("result", response.isExists());
			if (response.isExists()) {
				result.put("data", response.getSource());
			}
		} catch (IOException ioe) {
			logger.error("failed to get document, document id is {}", documentId);
			logger.error(ioe.getMessage());
		}

		helper.closeClient(client);
		return result;
	}

	@Override
	public JsonObject createDocument(String index, String documentId, JsonObject source) {
		JsonObject result = new JsonObject();
		RestHighLevelClient client = helper.getClient(options);

		try {
			IndexRequest request = new IndexRequest(index)
				.id(documentId)
				.source(source.getMap());

			IndexResponse response  = client.index(request, RequestOptions.DEFAULT);

			result
				.put("index", response.getIndex())
				.put("id", response.getId())
				.put("result", response.getResult() == DocWriteResponse.Result.CREATED);
		} catch (IOException ioe) {
			logger.error("failed to create document, document id is {0}", documentId);
			logger.error(ioe.getMessage());
		}

		helper.closeClient(client);
		return result;
	}

	@Override
	public JsonObject updateDocument(String index, String documentId, JsonObject source) {
		JsonObject result = new JsonObject();
		RestHighLevelClient client = helper.getClient(options);

		try {
			UpdateRequest request = new UpdateRequest()
				.index(index)
				.id(documentId)
				.doc(source.getMap());

			UpdateResponse response = client.update(request, RequestOptions.DEFAULT);

			result
				.put("index", response.getIndex())
				.put("id", response.getId())
				.put("result", response.getResult() == DocWriteResponse.Result.UPDATED);
		} catch (IOException ioe) {
			logger.error("failed to update document, document id is {}", documentId);
			logger.error(ioe.getMessage());
		}

		helper.closeClient(client);
		return result;
	}

	@Override
	public JsonObject deleteDocument(String index, String documentId) {
		JsonObject result = new JsonObject();
		RestHighLevelClient client = helper.getClient(options);

		try {
			DeleteRequest request = new DeleteRequest()
				.index(index)
				.id(documentId);

			DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);

			result
				.put("index", response.getIndex())
				.put("id", response.getId())
				.put("result", response.getResult() == DocWriteResponse.Result.DELETED);
		} catch (IOException ioe) {
			logger.error("failed to delete document, document id is {}", documentId);
			logger.error(ioe.getMessage());
		}

		helper.closeClient(client);
		return result;
	}

	@Override
	public JsonObject search(JsonObject params) {
		JsonObject result = new JsonObject();
		RestHighLevelClient client = helper.getClient(options);

		try {
			Fn.outWeb(!params.containsKey("index"), _404IndexNameMissingExceptionn.class, this.getClass());
			Fn.outWeb(!params.containsKey("searchText"), _404SearchTextMissingExceptionn.class, this.getClass());

			final String index = params.getString("inndex");
			final String searchText = params.getString("searchText");
			final int from = params.containsKey("from") ? params.getInteger("from") : 0;
			final int size = params.containsKey("size") ? params.getInteger("size") : 10;

			SearchRequest request = new SearchRequest(index)
				.source(helper.searchSourceBuilder(searchText, from, size));

			SearchResponse response = client.search(request, RequestOptions.DEFAULT);

			result
				.put("status", response.status().name())
				.put("took", response.getTook().seconds())
				.put("aggregations",
					response.getAggregations()
						.get(Aggregations.AGGREGATIONS_FIELD)
						.getMetaData()
						.get(Aggregation.CommonFields.BUCKETS.getPreferredName())
				)
				.put("total", response.getHits().getTotalHits())
				.put("hits", response.getHits().getHits());
		} catch (IOException ioe) {
			logger.error("failed to get search result from elasticsearch");
			logger.error(ioe.getMessage());
		}

		helper.closeClient(client);
		return result;
	}
}
