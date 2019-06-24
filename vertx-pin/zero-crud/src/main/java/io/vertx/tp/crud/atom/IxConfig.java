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
    /*
     * GET /api/columns/{actor}/full
     * Support, must be implemented with extension here.
     * This component is for ui render instead of column modification/stored
     */
    @JsonSerialize(using = ClassSerializer.class)
    @JsonDeserialize(using = ClassDeserializer.class)
    private Class<?> columnComponent;
    /*
     * GET /api/columns/{actor}/my
     * POST /api/columns/{actor}/my
     * Support, must be implemented with extension here.
     * Be careful, FULL / MY data source came from different position, that's
     * why there exist two component to do it.
     */
    @JsonSerialize(using = ClassSerializer.class)
    @JsonDeserialize(using = ClassDeserializer.class)
    private Class<?> columnMyComponent;
    /*
     * Inner system to seek impact resource or resources.
     */
    @JsonSerialize(using = ClassSerializer.class)
    @JsonDeserialize(using = ClassDeserializer.class)
    private Class<?> seekerComponent;
    /*
     * Config for seeker component
     */
    @JsonSerialize(using = JsonObjectSerializer.class)
    @JsonDeserialize(using = JsonObjectDeserializer.class)
    private JsonObject seekerConfig;

    public Class<?> getColumnComponent() {
        return this.columnComponent;
    }

    public void setColumnComponent(final Class<?> columnComponent) {
        this.columnComponent = columnComponent;
    }

    public Class<?> getColumnMyComponent() {
        return this.columnMyComponent;
    }

    public void setColumnMyComponent(final Class<?> columnMyComponent) {
        this.columnMyComponent = columnMyComponent;
    }

    public Class<?> getSeekerComponent() {
        return this.seekerComponent;
    }

    public void setSeekerComponent(final Class<?> seekerComponent) {
        this.seekerComponent = seekerComponent;
    }

    public JsonObject getSeekerConfig() {
        return this.seekerConfig;
    }

    public void setSeekerConfig(final JsonObject seekerConfig) {
        this.seekerConfig = seekerConfig;
    }

    @Override
    public String toString() {
        return "IxConfig{" +
                "columnComponent=" + this.columnComponent +
                ", columnMyComponent=" + this.columnMyComponent +
                ", seekerComponent=" + this.seekerComponent +
                ", seekerConfig=" + this.seekerConfig +
                '}';
    }
}
