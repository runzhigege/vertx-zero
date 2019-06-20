package io.vertx.tp.crud.atom;

import com.fasterxml.jackson.databind.ClassDeserializer;
import com.fasterxml.jackson.databind.ClassSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

public class IxConfig implements Serializable {
    /*
     * /api/columns/{actor}/full
     * /api/columns/{actor}/my
     * Support, must be implemented with extension here.
     */
    @JsonSerialize(using = ClassSerializer.class)
    @JsonDeserialize(using = ClassDeserializer.class)
    private Class<?> columnComponent;

    public Class<?> getColumnComponent() {
        return this.columnComponent;
    }

    public void setColumnComponent(final Class<?> columnComponent) {
        this.columnComponent = columnComponent;
    }

    @Override
    public String toString() {
        return "IxConfig{" +
                "componentColumn=" + this.columnComponent +
                '}';
    }
}
