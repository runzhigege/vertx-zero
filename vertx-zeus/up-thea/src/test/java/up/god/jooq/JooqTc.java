package up.god.jooq;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.plugin.jooq.JooqInfix;
import io.vertx.up.aiki.Ux;
import io.vertx.up.aiki.UxJooq;
import io.vertx.up.log.Annal;
import io.vertx.zero.marshal.node.Node;
import io.vertx.zero.marshal.node.ZeroUniform;
import io.zero.epic.Ut;
import io.zero.quiz.JooqBase;
import org.junit.BeforeClass;
import up.god.domain.tables.daos.SysTabularDao;
import up.god.domain.tables.pojos.SysTabular;

import java.util.UUID;

public abstract class JooqTc extends JooqBase {

    private static boolean CONNECTED = false;
    private transient final Node<JsonObject> uniform = Ut.singleton(ZeroUniform.class);

    @BeforeClass
    public static void setUp() {
        final Vertx vertx = Vertx.vertx();
        try {
            JooqInfix.init(vertx);
            CONNECTED = true;
        } catch (final Throwable ex) {
            final Annal annal = Annal.get(JooqTc.class);
            annal.jvm(ex);
        }
    }

    public SysTabular getTabular(final String file) {
        final JsonObject normalized = this.processData(this.getJson(file));
        // Formatted
        this.getLogger().info("[T] Normalized: {0}", normalized.encodePrettily());
        return Ux.fromJson(normalized, SysTabular.class, "tabular");
    }

    private JsonObject processData(final JsonObject data) {
        final JsonObject processed = data.copy();
        Ut.itJObject(data, (value, key) -> {
            if ("$GUID$".equals(value)) {
                processed.put(key, UUID.randomUUID().toString());
            }
            if (value.toString().startsWith("$RANDOM$")) {
                final Integer integer = Integer.parseInt(value.toString().split(",")[1]);
                processed.put(key, Ut.randomString(integer));
            }
        });
        return processed;
    }

    @Override
    public UxJooq getDao() {
        return CONNECTED ? Ux.Jooq.on(SysTabularDao.class)
                .on("tabular") : null;
    }
}
