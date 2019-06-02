package io.vertx.up.rs.hunt;

import io.vertx.core.json.JsonObject;
import io.vertx.up.atom.Envelop;
import io.vertx.up.plugin.auditor.ZeroAuditor;
import io.vertx.zero.marshal.node.Node;
import io.vertx.zero.marshal.node.ZeroUniform;
import io.zero.epic.Ut;

import java.util.Objects;

/*
 * Auditor Enabled for ( Version 1 )
 */
class Auditor {
    private static final String AUDITOR = "auditor";

    private static transient final Node<JsonObject> UNIFORM = Ut.singleton(ZeroUniform.class);

    static void audit(final Envelop envelop) {
        final JsonObject uniform = UNIFORM.read();
        if (uniform.containsKey(AUDITOR)) {
            final JsonObject auditorConfig = uniform.getJsonObject(AUDITOR);
            final Class<?> auditCls = Ut.clazz(auditorConfig.getString("component"));

            if (Objects.nonNull(auditCls)) {
                /*
                 * Extend ZeroAuditor for auditing system setting for some spec business.
                 */
                final ZeroAuditor auditor = Ut.singleton(auditCls);
                auditor.audit(envelop);
            }
        }
    }
}
