package io.vertx.up.secure;

import io.vertx.tp.plugin.mongo.MongoWall;
import io.vertx.up.atom.secure.Cliff;
import io.vertx.up.eon.em.WallType;
import io.vertx.up.secure.jwt.JwtWall;
import io.vertx.up.uca.marshal.Transformer;
import io.vertx.up.util.Ut;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

interface Wall {

    String TYPE = "type";
}

interface Pool {
    ConcurrentMap<WallType, Transformer<Cliff>>
            WALL_TRANSFORMER = new ConcurrentHashMap<WallType, Transformer<Cliff>>() {
        {
            put(WallType.MONGO, Ut.singleton(MongoWall.class));
            put(WallType.JWT, Ut.singleton(JwtWall.class));
        }
    };
}