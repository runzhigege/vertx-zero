package io.vertx.up.jooq;

import com.htl.cv.Pojo;
import com.htl.domain.tables.daos.SysAppDao;
import com.htl.domain.tables.pojos.SysApp;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.plugin.jooq.JooqInfix;
import io.vertx.up.aiki.Ux;
import io.vertx.up.aiki.UxJooq;
import io.zero.epic.Ut;
import io.zero.quiz.JooqBase;
import org.junit.Test;

import java.util.UUID;

public class JooqAppTc extends JooqBase {

    public static void setUp() {
        final Vertx vertx = Vertx.vertx();
        JooqInfix.init(vertx);
    }

    @Override
    public UxJooq getDao() {
        return Ux.Jooq.on(SysAppDao.class, JooqInfix.getDao(SysAppDao.class));
    }

    public void testNonField() {
        this.async(this.getDao()
                        .on("")
                        .fetchOneAsync("name", "vie.app.htl"),
                // Callback Consumer
                (item, context) -> context.assertNotNull(item));
    }

    public void testFetchOneAsync() {
        // No mojo
        this.fetchOneAsync(SysAppDao.class, null,
                "S_NAME", "vie.app.htl",
                "sName", "vie.app.htl");
        // Mojo
        this.fetchOneAsync(SysAppDao.class, Pojo.APP,
                "S_NAME", "vie.app.htl",
                "sName", "vie.app.htl",
                "name", "vie.app.htl");
    }

    public void testFetchOneAndAsync() {
        // No mojo
        this.fetchOneAndAsync(SysAppDao.class, null,
                "fetchOneAsync1.json",
                "fetchOneAsync2.json");
        // Mojo\
        this.fetchOneAndAsync(SysAppDao.class, Pojo.APP,
                "fetchOneAsync1.json",
                "fetchOneAsync2.json",
                "fetchOneAsync3.json");
    }

    @Test
    public void testFetchFields() {
        final SysApp app = new SysApp();
        app.setPkId(UUID.randomUUID().toString());
        app.setSName("Lang");
        app.setSCode("LANG.APP");
        app.setIsActive(Boolean.TRUE);
        app.setIAttachSize(12);
        final JsonObject json = Ut.serializeJson(app);
        System.out.println(json.encodePrettily());
        final SysApp target = Ut.deserialize(json, SysApp.class);
        System.out.println(target);
    }
}
