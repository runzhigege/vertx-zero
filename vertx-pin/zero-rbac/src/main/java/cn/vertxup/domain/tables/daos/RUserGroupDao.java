/*
 * This file is generated by jOOQ.
*/
package cn.vertxup.domain.tables.daos;


import cn.vertxup.domain.tables.RUserGroup;
import cn.vertxup.domain.tables.records.RUserGroupRecord;

import io.github.jklingsporn.vertx.jooq.future.VertxDAO;

import java.util.List;

import javax.annotation.Generated;

import org.jooq.Configuration;
import org.jooq.Record2;
import org.jooq.impl.DAOImpl;


import java.util.concurrent.CompletableFuture;
import io.github.jklingsporn.vertx.jooq.future.util.FutureTool;
/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.8"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class RUserGroupDao extends DAOImpl<RUserGroupRecord, cn.vertxup.domain.tables.pojos.RUserGroup, Record2<String, String>> implements VertxDAO<cn.vertxup.domain.tables.records.RUserGroupRecord,cn.vertxup.domain.tables.pojos.RUserGroup,org.jooq.Record2<java.lang.String, java.lang.String>> {

    /**
     * Create a new RUserGroupDao without any configuration
     */
    public RUserGroupDao() {
        super(RUserGroup.R_USER_GROUP, cn.vertxup.domain.tables.pojos.RUserGroup.class);
    }

    /**
     * Create a new RUserGroupDao with an attached configuration
     */
    public RUserGroupDao(Configuration configuration) {
        super(RUserGroup.R_USER_GROUP, cn.vertxup.domain.tables.pojos.RUserGroup.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Record2<String, String> getId(cn.vertxup.domain.tables.pojos.RUserGroup object) {
        return compositeKeyRecord(object.getGroupId(), object.getUserId());
    }

    /**
     * Fetch records that have <code>GROUP_ID IN (values)</code>
     */
    public List<cn.vertxup.domain.tables.pojos.RUserGroup> fetchByGroupId(String... values) {
        return fetch(RUserGroup.R_USER_GROUP.GROUP_ID, values);
    }

    /**
     * Fetch records that have <code>USER_ID IN (values)</code>
     */
    public List<cn.vertxup.domain.tables.pojos.RUserGroup> fetchByUserId(String... values) {
        return fetch(RUserGroup.R_USER_GROUP.USER_ID, values);
    }

    /**
     * Fetch records that have <code>GROUP_ID IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.domain.tables.pojos.RUserGroup>> fetchByGroupIdAsync(List<String> values) {
        return fetchAsync(RUserGroup.R_USER_GROUP.GROUP_ID,values);
    }

    /**
     * Fetch records that have <code>USER_ID IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.domain.tables.pojos.RUserGroup>> fetchByUserIdAsync(List<String> values) {
        return fetchAsync(RUserGroup.R_USER_GROUP.USER_ID,values);
    }

    private io.vertx.core.Vertx vertx;

    @Override
    public void setVertx(io.vertx.core.Vertx vertx) {
        this.vertx = vertx;
    }

    @Override
    public io.vertx.core.Vertx vertx() {
        return this.vertx;
    }

}
