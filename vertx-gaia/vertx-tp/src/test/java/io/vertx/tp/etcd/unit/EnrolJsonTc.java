package io.vertx.tp.etcd.unit;

import io.vertx.core.json.JsonObject;
import io.vertx.tp.etcd.Enrol;
import io.vertx.tp.etcd.center.EtcdData;
import io.zero.epic.mirror.Instance;
import io.zero.quiz.TestBase;

public class EnrolJsonTc extends TestBase {

    public void testWrite() {
        final Enrol<JsonObject> enrol = Instance.singleton(JObjectEnrol.class);
        enrol.write("/zero/ipc/192.168.0.100/8080/", this.getJson("Store.json"));
    }

    public void testRead() {
        final Enrol<JsonObject> enrol = Instance.singleton(JObjectEnrol.class);
        final JsonObject data = enrol.read("/zero/ipc/192.168.0.100/8080/");
        System.out.println(data);
    }

    public void testDelete() {
        final EtcdData etcdData = EtcdData.create(this.getClass());
        etcdData.delete("zero/ipc/services/ipc-cronus/0.0.0.0/6884");
    }

    public void testReadDir() {
        final EtcdData etcdData = EtcdData.create(this.getClass());
        etcdData.read("zero/endpoint/services");
    }
}
