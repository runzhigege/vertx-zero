package io.vertx.tp.plugin.elasticsearch;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.up.fn.Fn;
import io.vertx.up.log.Annal;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;

import java.io.IOException;

/**
 * @author Hongwei
 * @since 2019/12/29, 13:31
 */

public class ElasticSearchClientImpl implements ElasticSearchClient {
	private static final Annal logger = Annal.get(ElasticSearchClientImpl.class);

	private transient final Vertx vertx;
	private transient final ElasticSearchHelper helper = ElasticSearchHelper.helper(this.getClass());

	ElasticSearchClientImpl(final Vertx vertx) {
		this.vertx = vertx;
	}

	@Override
	public JsonObject getIndex(String index) {
		JsonObject result = new JsonObject();
		RestHighLevelClient client = helper.getClient();

		try {
			GetIndexRequest request = new GetIndexRequest(index);
			request.includeDefaults(true);
			request.indicesOptions(IndicesOptions.lenientExpandOpen());

			GetIndexResponse response = client.indices().get(request, RequestOptions.DEFAULT);

			result.put("index", response.getIndices())
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
	public JsonObject createIndex(String alias, String index, int numberOfShards, int numberOfReplicas, JsonObject mappings) {
		CreateIndexRequest request = new CreateIndexRequest(index);
		request.alias(new Alias(alias));
		request.settings(helper.settingsBuilder(numberOfShards, numberOfReplicas));
		request.mapping(helper.mappingsBuilder(mappings));

		return null;
	}

	@Override
	public JsonObject updateIndex(String index, int numberOfShards, int numberOfReplicas) {
		return null;
	}

	@Override
	public JsonObject deleteIndex(String index) {
		return null;
	}
}
