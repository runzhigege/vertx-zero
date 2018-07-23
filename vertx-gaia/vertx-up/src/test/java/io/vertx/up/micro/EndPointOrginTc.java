package io.vertx.up.micro;

import io.vertx.servicediscovery.Record;
import io.vertx.tp.etcd.center.EtcdData;
import io.vertx.up.micro.discovery.ApiOrigin;
import io.vertx.up.micro.discovery.Origin;
import io.vertx.up.epic.mirror.Instance;

import java.util.concurrent.ConcurrentMap;

public class EndPointOrginTc {

    public void testRead() {
        final Origin orgin = Instance.singleton(ApiOrigin.class);
        final ConcurrentMap<String, Record> records = orgin.getRegistryData();
        records.forEach((key, value) -> {
            System.out.println(key);
            System.out.println(value.toJson().encodePrettily());
        });
    }

    public void testNodes() {
        final EtcdData data = EtcdData.create(getClass());
    }
}
