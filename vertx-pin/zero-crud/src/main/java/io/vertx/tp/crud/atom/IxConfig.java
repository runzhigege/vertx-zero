package io.vertx.tp.crud.atom;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.io.Serializable;

public class IxConfig implements Serializable {

    private String name;
    private String pojo;
    private String keyField;

    @JsonSerialize(using = ClassSerializer.class)
    @JsonDeserialize(using = ClassDeserializer.class)
    private Class<?> pojoCls;

    @JsonSerialize(using = ClassSerializer.class)
    @JsonDeserialize(using = ClassDeserializer.class)
    private Class<?> daoCls;

    @JsonSerialize(using = JsonArraySerializer.class)
    @JsonDeserialize(using = JsonArrayDeserializer.class)
    private JsonArray uniqueField;

    @JsonSerialize(using = JsonObjectSerializer.class)
    @JsonDeserialize(using = JsonObjectDeserializer.class)
    private JsonObject auditorField;

    public String getKeyField() {
        return this.keyField;
    }

    public void setKeyField(final String keyField) {
        this.keyField = keyField;
    }

    public JsonObject getAuditorField() {
        return this.auditorField;
    }

    public void setAuditorField(final JsonObject auditorField) {
        this.auditorField = auditorField;
    }

    public JsonArray getUniqueField() {
        return this.uniqueField;
    }

    public void setUniqueField(final JsonArray uniqueField) {
        this.uniqueField = uniqueField;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPojo() {
        return this.pojo;
    }

    public void setPojo(final String pojo) {
        this.pojo = pojo;
    }

    public Class<?> getPojoCls() {
        return this.pojoCls;
    }

    public void setPojoCls(final Class<?> pojoCls) {
        this.pojoCls = pojoCls;
    }

    public Class<?> getDaoCls() {
        return this.daoCls;
    }

    public void setDaoCls(final Class<?> daoCls) {
        this.daoCls = daoCls;
    }
}
