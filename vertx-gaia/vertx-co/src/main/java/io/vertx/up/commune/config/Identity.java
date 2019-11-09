package io.vertx.up.commune.config;

import java.io.Serializable;
import java.util.Objects;

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
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Identity)) return false;
        final Identity identity = (Identity) o;
        return this.identifier.equals(identity.identifier) &&
                Objects.equals(this.identifierComponent, identity.identifierComponent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.identifier, this.identifierComponent);
    }

    @Override
    public String toString() {
        return "Identifier{" +
                "identifier='" + this.identifier + '\'' +
                ", identifierComponent=" + this.identifierComponent +
                '}';
    }
}
