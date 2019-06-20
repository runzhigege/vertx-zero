package io.vertx.tp.crud.atom;

import com.fasterxml.jackson.databind.ClassDeserializer;
import com.fasterxml.jackson.databind.ClassSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

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
    private Class<?> columnMyComponent;

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

    @Override
    public String toString() {
        return "IxConfig{" +
                "componentColumn=" + this.columnComponent +
                '}';
    }
}
