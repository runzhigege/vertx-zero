package io.vertx.tp.plugin.elasticsearch;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

import java.util.List;
import java.util.Set;

/**
 * @author Hongwei
 * @since 2019/12/29, 13:27
 */
public interface ElasticSearchClient {
	static ElasticSearchClient createShared(final Vertx vertx, final JsonObject options) {
		return new ElasticSearchClientImpl(vertx, options);
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
	 * @param index name of index. this is real index name
	 * @param numberOfShards number of shards, default is 5
	 * @param numberOfReplicas number of replicas, default is 3
	 * @param mappings fields were used to create index mapping
	 * @return JsonObject like below
	 * {
	 *  "isAcknowledged": true
	 * }
	 */
	public JsonObject createIndex(final String alias, final String index, int numberOfShards, int numberOfReplicas, final JsonObject mappings);

	/**
	 * delete index
	 * @param index name of index
	 * @param numberOfShards number of shards
	 * @param numberOfReplicas number of replicas
	 * @return JsonObject like below
	 * {
	 *  "isAcknowledged": true
	 * }
	 */
	public JsonObject updateIndex(final String index, int numberOfShards, int numberOfReplicas);

	/**
	 * delete index by name
	 * @param index name of index
	 * @return JsonObject like below
	 * {
	 *  "isAcknowledged": true
	 * }
	 */
	public JsonObject deleteIndex(final String index);

	/* document API */

	/**
	 * get document by document id
	 * @param index name of index
	 * @param documentId document id
	 * @return JsonObject like below
	 * {
	 *     "index"; "",
	 *     "id": "",
	 *     "result": true / false,
	 *     "data": {}
	 * }
	 */
	public JsonObject getDocument(final String index, final String documentId);

	/**
	 * create document from json object, must specify document id
	 * @param index name of index
	 * @param documentId document id
	 * @param source json object of document
	 * @return JsonObject like below
	 * {
	 *     "index"; "",
	 *     "id": "",
	 *     "result": true / false
	 * }
	 */
	public JsonObject createDocument(final String index, final String documentId, final JsonObject source);

	/**
	 * update document from json object, must specify document id
	 * @param index name of index
	 * @param documentId document id
	 * @param source json object of document
	 * @return JsonObject like below
	 * {
	 *     "index"; "",
	 *     "id": "",
	 *     "result": true / false
	 * }
	 */
	public JsonObject updateDocument(final String index, final String documentId, final JsonObject source);

	/**
	 * delete document by document id
	 * @param index name of index
	 * @param documentId document id
	 * @return JsonObject like below
	 * {
	 *     "index"; "",
	 *     "id": "",
	 *     "result": true / false
	 * }
	 */
	public JsonObject deleteDocument(final String index, final String documentId);

	/* full test search API */
	/**
	 * get search result from ElasticSearch by search text
	 * @param index name of index
	 * @param searchText search text entered by users
	 * @param from start searching from, default is 0
	 * @param size the number of search hits to return, default is 10
	 * @return JsonObject like below
	 * {
	 *     "status": "OK",
	 *     "took": 1,
	 *     "aggregations": [
	 *         {
	 *             "key": "cmdb",
	 *             "doc_count": 10
	 *         },
	 *         ...
	 *     ],
	 *     "total": 10,
	 *     "hits": [
	 *         {
	 *             "_index": "aaa",
	 *             "_type": "_doc",
	 *             "_id": "2",
	 *             "_score": 1.1507283,
	 *             "_source": {},
	 *             "highlight": {}
	 *         },
	 *         ...
	 *     ]
	 * }
	 */
	public JsonObject search(final String index, final String searchText, int from, int size);
}
