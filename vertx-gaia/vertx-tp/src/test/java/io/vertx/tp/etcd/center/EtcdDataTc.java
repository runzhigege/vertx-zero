package io.vertx.tp.etcd.center;

import io.zero.quiz.EpicBase;

public class EtcdDataTc extends EpicBase {

    public void testData() {
        EtcdData.create(this.getClass());
    }
}
