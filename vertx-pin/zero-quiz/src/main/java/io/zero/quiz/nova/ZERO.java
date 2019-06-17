package io.zero.quiz.nova;

import cn.vertxup.api.*;
import io.vertx.core.Future;
import io.vertx.up.atom.Envelop;
import io.zero.epic.Ut;
import io.zero.quiz.cv.QzApi;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

interface Actors {
    PostActor POST = Ut.singleton(PostActor.class);
    GetActor GET = Ut.singleton(GetActor.class);
    PutActor PUT = Ut.singleton(PutActor.class);
    DeleteActor DELETE = Ut.singleton(DeleteActor.class);
    QueryActor QUERY = Ut.singleton(QueryActor.class);
}

interface Pool {

    ConcurrentMap<Class<?>, Qz> QZ_POOL = new ConcurrentHashMap<>();

    ConcurrentMap<QzApi, Function<Envelop, Future<Envelop>>> METHOD_POOL =
            new ConcurrentHashMap<QzApi, Function<Envelop, Future<Envelop>>>() {
                {
                    this.put(QzApi.POST_ACTOR, Actors.POST::create);
                    this.put(QzApi.GET_ACTOR_KEY, Actors.GET::getById);
                    this.put(QzApi.PUT_ACTOR_KEY, Actors.PUT::update);
                    this.put(QzApi.DELETE_ACTOR_KEY, Actors.DELETE::delete);

                    this.put(QzApi.POST_ACTOR_SEARCH, Actors.QUERY::search);
                    this.put(QzApi.POST_ACTOR_MISSING, Actors.QUERY::missing);
                    this.put(QzApi.POST_ACTOR_EXISTING, Actors.QUERY::existing);

                    this.put(QzApi.POST_BATCH_UPDATE, Actors.PUT::updateBatch);
                    this.put(QzApi.POST_BATCH_DELETE, Actors.DELETE::deleteBatch);
                }
            };
}
