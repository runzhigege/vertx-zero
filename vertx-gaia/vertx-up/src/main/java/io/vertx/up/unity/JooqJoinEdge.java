package io.vertx.up.unity;

import io.vertx.up.util.Ut;

/*
 * Helper for join
 */
class JooqJoinEdge {
    private transient String fromTable;
    private transient String fromField;
    private transient String toTable;
    private transient String toField;

    JooqJoinEdge() {
    }

    void setFrom(final String table, final String field) {
        this.fromTable = table;
        this.fromField = Ut.notNil(field) ? field : "key";
    }

    void setTo(final String table, final String field) {
        this.toTable = table;
        this.toField = Ut.notNil(field) ? field : "key";
    }

    String getFromTable() {
        return this.fromTable;
    }

    String getFromField() {
        return this.fromField;
    }

    String getToTable() {
        return this.toTable;
    }

    String getToField() {
        return this.toField;
    }

    @Override
    public String toString() {
        return "JooqJoinEdge{" +
                "fromTable='" + this.fromTable + '\'' +
                ", fromField='" + this.fromField + '\'' +
                ", toTable='" + this.toTable + '\'' +
                ", toField='" + this.toField + '\'' +
                '}';
    }
}
