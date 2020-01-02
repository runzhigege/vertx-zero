package io.vertx.tp.plugin.elasticsearch;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

/**
 * @author Hongwei
 * @since 2019/12/29, 13:27
 */
public interface ElasticSearchClient {
	static ElasticSearchClient createShared(final Vertx vertx) {
		return new ElasticSearchClientImpl(vertx);
	}

	/* index API */

	/**
	 * get index information
	 * @param indexName name of indexs
	 * @return JsonObject for index information
	 */
	public JsonObject getIndex(final String index);

	/**
	 * create index with settings and mappings
	 * @param alias alias name of index, it's used to fulltext search
	 * @param index name of index. this is real index name, it's better to add version number as suffix, like service_v1
	 * @param numberOfShards number of shards, default is 5
	 * @param numberOfReplicas number of replicas, default is 3
	 * @param mappings fields were used to create index mapping
	 * @return JsonObject like below
	 * {
	 *  "acknowledged": true,
	 *  "shards_acknowledged": true,
	 *  "index": "${indexName}",
	 *  "alias": "${aliasName}"
	 * }
	 */
	public JsonObject createIndex(final String alias, final String index, final int numberOfShards, final int numberOfReplicas, final JsonObject mappings);

	/**
	 * delete index
	 * @param index name of index
	 * @param numberOfShards number of shards
	 * @param numberOfReplicas number of replicas
	 * @return JsonObject like below
	 * {
	 *  "acknowledged": true,
	 *  "shards_acknowledged": true,
	 *  "index": "${indexName}"
	 * }
	 */
	public JsonObject updateIndex(final String index, final int numberOfShards, final int numberOfReplicas);

	/**
	 * delete index by name
	 * @param index name of index
	 * @return JsonObject like below
	 * {
	 *  "acknowledged": true
	 * }
	 */
	public JsonObject deleteIndex(final String index);

	/* document API */
	public JsonObject getDocument();
	public JsonObject createDocument();
	public JsonObject updateDocument();
	public JsonObject deleteDocument();

	/* full test search API */
	public JsonObject search(final String text);
}
