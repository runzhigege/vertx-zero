package io.vertx.up.jooq;

import com.htl.cv.Pojo;
import com.htl.domain.tables.daos.SysAppDao;
import com.htl.domain.tables.daos.SysTabularDao;
import com.htl.domain.tables.pojos.SysApp;
import com.htl.domain.tables.pojos.SysTabular;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.TestContext;
import io.vertx.tp.plugin.jooq.JooqInfix;
import io.vertx.up.aiki.Ux;
import io.vertx.up.aiki.UxJooq;
import io.vertx.up.atom.Envelop;
import io.zero.epic.Ut;
import io.zero.quiz.JooqBase;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.UUID;

public class JooqAppTc extends JooqBase {

    @BeforeClass
    public static void setUp() {
        final Vertx vertx = Vertx.vertx();
        JooqInfix.init(vertx);
    }

    @Override
    public UxJooq getDao() {
        return Ux.Jooq.on(SysAppDao.class, JooqInfix.getDao(SysAppDao.class));
    }

    public void testNonField(final TestContext context) {
        this.asyncFlow(context,
                this.getDao()
                        .on("")
                        .fetchOneAsync("name", "vie.app.htl"),
                // Callback Consumer
                context::assertNotNull);
    }

    public void testFetchOneAsync(final TestContext context) {
        // No mojo
        this.fetchOneAsync(context, SysAppDao.class, null,
                "S_NAME", "vie.app.htl",
                "sName", "vie.app.htl");
        // Mojo
        this.fetchOneAsync(context, SysAppDao.class, Pojo.APP,
                "S_NAME", "vie.app.htl",
                "sName", "vie.app.htl",
                "name", "vie.app.htl");
    }

    public void testFetchOneAndAsync(final TestContext context) {
        // No mojo
        this.fetchOneAndAsync(context, SysAppDao.class, null,
                "fetchOneAsync1.json",
                "fetchOneAsync2.json");
        // Mojo\
        this.fetchOneAndAsync(context, SysAppDao.class, Pojo.APP,
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

    @Test
    public void testApp() {
        final Envelop envelop = Envelop.success(this.getJson("app.json"));
        final SysApp app = Ux.fromEnvelop(envelop, SysApp.class, Pojo.APP);
        System.out.println(app);
    }

    public void testUpsert(final TestContext context) {
        final SysTabular entity = Ux.fromJson(this.getJson("tabular.json"), SysTabular.class, Pojo.TABULAR)
                .setZCreateTime(LocalDateTime.now())
                .setZUpdateTime(LocalDateTime.now())
                .setPkId(UUID.randomUUID().toString());
        this.asyncFlow(context,
                Ux.Jooq.on(SysTabularDao.class)
                        .on(Pojo.TABULAR)
                        .upsertAsync(Ux.toFilters(new String[]{"sigma", "code"},
                                entity::getZSigma,
                                entity::getSCode), entity)
                        .compose(Ux.fnJObject(Pojo.TABULAR)),
                item -> {
                    context.assertNotNull(item);
                    System.out.println(item.encodePrettily());
                });
    }
}
