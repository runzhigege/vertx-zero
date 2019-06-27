package io.vertx.tp.crud.atom;

import com.fasterxml.jackson.databind.ClassDeserializer;
import com.fasterxml.jackson.databind.ClassSerializer;
import com.fasterxml.jackson.databind.JsonObjectDeserializer;
import com.fasterxml.jackson.databind.JsonObjectSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.vertx.core.json.JsonObject;

import java.io.Serializable;

public class IxModule implements Serializable {

    private String name;
    private String table;
    private String pojo;
    private IxField field;
    private IxColumn column;

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

    public IxColumn getColumn() {
        return this.column;
    }

    public void setColumn(final IxColumn column) {
        this.column = column;
    }

    public String getTable() {
        return this.table;
    }

    public void setTable(final String table) {
        this.table = table;
    }

    @Override
    public String toString() {
        return "IxModule{" +
                "name='" + this.name + '\'' +
                ", table='" + this.table + '\'' +
                ", pojo='" + this.pojo + '\'' +
                ", field=" + this.field +
                ", column=" + this.column +
                ", pojoCls=" + this.pojoCls +
                ", daoCls=" + this.daoCls +
                ", header=" + this.header +
                '}';
    }
}
