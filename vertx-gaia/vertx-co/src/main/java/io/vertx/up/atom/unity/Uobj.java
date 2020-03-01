package io.vertx.up.atom.unity;

/**
 * @author lang
 * Stream tool for <T> type of entity
 */
public class Uobj {

    private final transient Class<?> clazzT;

    private Uobj(final Class<?> clazzT) {
        this.clazzT = clazzT;
    }

    public static Uobj create(final Class<?> clazzT) {
        return new Uobj(clazzT);
    }
}
