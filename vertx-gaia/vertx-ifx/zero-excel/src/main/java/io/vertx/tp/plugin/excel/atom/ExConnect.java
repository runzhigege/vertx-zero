package io.vertx.tp.plugin.excel.atom;

import io.vertx.core.json.JsonArray;

import java.io.Serializable;

/**
 * Connect configuration data to
 * Dao / Pojo class
 */
public class ExConnect implements Serializable {

    private transient String table;
    private transient Class<?> pojo;
    private transient Class<?> dao;
    private transient JsonArray unique;

    public String getTable() {
        return this.table;
    }

    public void setTable(final String table) {
        this.table = table;
    }

    public Class<?> getPojo() {
        return this.pojo;
    }

    public void setPojo(final Class<?> pojo) {
        this.pojo = pojo;
    }

    public Class<?> getDao() {
        return this.dao;
    }

    public void setDao(final Class<?> dao) {
        this.dao = dao;
    }

    public JsonArray getUnique() {
        return this.unique;
    }

    public void setUnique(final JsonArray unique) {
        this.unique = unique;
    }

    @Override
    public String toString() {
        return "ExConnect{" +
                "table='" + this.table + '\'' +
                ", pojo=" + this.pojo +
                ", dao=" + this.dao +
                ", unique=" + this.unique +
                '}';
    }
}
