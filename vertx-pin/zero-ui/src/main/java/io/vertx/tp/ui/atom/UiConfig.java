package io.vertx.tp.ui.atom;

import com.fasterxml.jackson.databind.ClassDeserializer;
import com.fasterxml.jackson.databind.ClassSerializer;
import com.fasterxml.jackson.databind.JsonObjectDeserializer;
import com.fasterxml.jackson.databind.JsonObjectSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.vertx.core.json.JsonObject;

import java.io.Serializable;

/*
 * Ui Configuration data
 */
public class UiConfig implements Serializable {

    private transient String configPath;

    @JsonSerialize(using = JsonObjectSerializer.class)
    @JsonDeserialize(using = JsonObjectDeserializer.class)
    private transient JsonObject configStatic;

    private transient Boolean supportDynamic;

    @JsonSerialize(using = ClassSerializer.class)
    @JsonDeserialize(using = ClassDeserializer.class)
    private transient Class<?> componentColumn;

    public String getConfigPath() {
        return this.configPath;
    }

    public void setConfigPath(final String configPath) {
        this.configPath = configPath;
    }

    public JsonObject getConfigStatic() {
        return this.configStatic;
    }

    public void setConfigStatic(final JsonObject configStatic) {
        this.configStatic = configStatic;
    }

    public Boolean getSupportDynamic() {
        return this.supportDynamic;
    }

    public void setSupportDynamic(final Boolean supportDynamic) {
        this.supportDynamic = supportDynamic;
    }

    public Class<?> getComponentColumn() {
        return this.componentColumn;
    }

    public void setComponentColumn(final Class<?> componentColumn) {
        this.componentColumn = componentColumn;
    }

    @Override
    public String toString() {
        return "UiConfig{" +
                "configPath='" + this.configPath + '\'' +
                ", configStatic=" + this.configStatic +
                ", supportDynamic=" + this.supportDynamic +
                ", componentColumn=" + this.componentColumn +
                '}';
    }
}
