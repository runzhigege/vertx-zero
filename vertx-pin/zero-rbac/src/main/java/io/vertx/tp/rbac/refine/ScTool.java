package io.vertx.tp.rbac.refine;

import io.vertx.tp.rbac.atom.ScConfig;
import io.vertx.tp.rbac.init.ScPin;
import io.vertx.up.log.Annal;
import io.zero.epic.Ut;

class ScTool {
    private static final Annal LOGGER = Annal.get(ScTool.class);
    private static final ScConfig CONFIG = ScPin.getConfig();

    static String generateCode() {
        final Integer codeLength = CONFIG.getCodeLength();
        final String authCode = Ut.randomString(codeLength);
        ScLog.infoAuth(LOGGER, "Generated Authorization Code: {0}", authCode);
        return authCode;
    }
}
