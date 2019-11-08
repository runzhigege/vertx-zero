package io.vertx.up.commune.config;

import java.io.Serializable;

/*
 * Identifier structure for identifier
 * 1) static identifier: the definition of direct
 * 2) dynamic identifier: the identifier came from identifierComponent
 */
public class Identity implements Serializable {
    private transient String identifier;
    private transient Class<?> identifierComponent;

    public String getIdentifier() {
        return this.identifier;
    }

    public void setIdentifier(final String identifier) {
        this.identifier = identifier;
    }

    public Class<?> getIdentifierComponent() {
        return this.identifierComponent;
    }

    public void setIdentifierComponent(final Class<?> identifierComponent) {
        this.identifierComponent = identifierComponent;
    }

    @Override
    public String toString() {
        return "Identifier{" +
                "identifier='" + this.identifier + '\'' +
                ", identifierComponent=" + this.identifierComponent +
                '}';
    }
}
