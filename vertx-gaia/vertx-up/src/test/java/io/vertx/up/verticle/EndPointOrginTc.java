package io.vertx.up.verticle;

import io.vertx.servicediscovery.Record;
import io.vertx.tp.plugin.etcd.center.EtcdData;
import io.vertx.up.uca.micro.discovery.ApiOrigin;
import io.vertx.up.uca.micro.discovery.Origin;
import io.vertx.up.util.Ut;

import java.util.concurrent.ConcurrentMap;

public class EndPointOrginTc {

    public void testRead() {
        final Origin orgin = Ut.singleton(ApiOrigin.class);
        final ConcurrentMap<String, Record> records = orgin.getRegistryData();
        records.forEach((key, value) -> {
            System.out.println(key);
            System.out.println(value.toJson().encodePrettily());
        });
    }

    public void testNodes() {
        final EtcdData data = EtcdData.create(this.getClass());
    }
}
