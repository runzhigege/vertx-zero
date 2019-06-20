package io.vertx.tp.crud.atom;

import io.zero.epic.Ut;

import java.io.Serializable;

public class IxColumn implements Serializable {

    private transient Boolean dynamic = Boolean.FALSE;
    private transient String identifier;

    public Boolean getDynamic() {
        /* Basic calculation for column extract mode */
        this.dynamic = Ut.isNil(this.identifier);
        return this.dynamic;
    }

    public void setDynamic(final Boolean dynamic) {
        this.dynamic = dynamic;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public void setIdentifier(final String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        return "IxColumn{" +
                "dynamic=" + this.dynamic +
                ", identifier='" + this.identifier + '\'' +
                '}';
    }
}
