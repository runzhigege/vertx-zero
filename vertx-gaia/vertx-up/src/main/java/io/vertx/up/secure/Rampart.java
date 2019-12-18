package io.vertx.up.secure;

import io.vertx.core.json.JsonObject;
import io.vertx.up.atom.secure.Cliff;
import io.vertx.up.eon.em.WallType;
import io.vertx.up.uca.marshal.Transformer;
import io.vertx.up.fn.Fn;

public class Rampart implements Transformer<Cliff> {

    @Override
    public Cliff transform(final JsonObject input) {
        return Fn.getJvm(() -> {
            if (input.containsKey(Wall.TYPE)) {
                // Standard
                final Transformer<Cliff> transformer =
                        Pool.WALL_TRANSFORMER
                                .get(WallType.from(input.getString(Wall.TYPE)));
                return transformer.transform(input);
            } else {
                // Non Standard
                // TODO: Custom building.
                return new Cliff();
            }
        }, input);
    }
}
