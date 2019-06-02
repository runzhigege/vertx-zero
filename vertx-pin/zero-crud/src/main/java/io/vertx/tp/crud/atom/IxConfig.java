package io.vertx.tp.crud.atom;

import com.fasterxml.jackson.databind.ClassDeserializer;
import com.fasterxml.jackson.databind.ClassSerializer;
import com.fasterxml.jackson.databind.JsonObjectDeserializer;
import com.fasterxml.jackson.databind.JsonObjectSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.vertx.core.json.JsonObject;

import java.io.Serializable;

public class IxConfig implements Serializable {

    private String name;
    private String pojo;
    private IxField field;

    @JsonSerialize(using = ClassSerializer.class)
    @JsonDeserialize(using = ClassDeserializer.class)
    private Class<?> pojoCls;

    @JsonSerialize(using = ClassSerializer.class)
    @JsonDeserialize(using = ClassDeserializer.class)
    private Class<?> daoCls;

    @JsonSerialize(using = JsonObjectSerializer.class)
    @JsonDeserialize(using = JsonObjectDeserializer.class)
    private JsonObject header;

    public IxField getField() {
        return this.field;
    }

    public void setField(final IxField field) {
        this.field = field;
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

    public JsonObject getHeader() {
        return this.header;
    }

    public void setHeader(final JsonObject header) {
        this.header = header;
    }

    @Override
    public String toString() {
        return "IxConfig{" +
                "name='" + this.name + '\'' +
                ", pojo='" + this.pojo + '\'' +
                ", field=" + this.field +
                ", pojoCls=" + this.pojoCls +
                ", daoCls=" + this.daoCls +
                ", header=" + this.header +
                '}';
    }
}
