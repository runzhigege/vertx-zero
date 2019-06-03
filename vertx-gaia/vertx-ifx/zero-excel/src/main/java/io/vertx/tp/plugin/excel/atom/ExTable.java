package io.vertx.tp.plugin.excel.atom;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ExTable implements Serializable {
    /* Field */
    private final transient List<String> fields = new ArrayList<>();
    private final transient List<ExRecord> values = new ArrayList<>();

    /* Metadata Row */
    private transient final String sheet;
    private transient String name;
    private transient String description;
    private transient Class<?> daoCls;

    public ExTable(final String sheet) {
        this.sheet = sheet;
    }

    /*
     * ( No Bean ) Iterator row of Add operation
     */
    public void add(final String field) {
        this.fields.add(field);
    }

    public void add(final ExRecord record) {
        this.values.add(record);
    }

    /*
     * Get row values of List, ExRecord row data.
     */
    public List<ExRecord> get() {
        return this.values;
    }

    /*
     * Extract field by index
     */
    public String field(final int index) {
        return this.fields.size() <= index ? null : this.fields.get(index);
    }

    public int size() {
        return this.fields.size();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ExTable)) {
            return false;
        }
        final ExTable table = (ExTable) o;
        return this.name.equals(table.name) &&
                this.sheet.equals(table.sheet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.sheet);
    }

    @Override
    public String toString() {
        final StringBuilder content = new StringBuilder();
        content.append("sheet = ").append(this.sheet).append(",");
        content.append("name = ").append(this.name).append(",");
        content.append("daoCls = ").append(this.daoCls).append(',');
        content.append("description = ").append(this.description).append("\n");
        this.fields.forEach(field -> content.append(field).append(","));
        content.append("\n");
        this.values.forEach(row -> content.append(row.toString()).append("\n"));
        return content.toString();
    }
}
