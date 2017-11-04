package io.vertx.zero.ke.option;

import com.vie.hors.ZeroException;
import com.vie.hors.system.ArgumentException;
import com.vie.util.Instance;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.zero.ke.Visitor;
import org.junit.Test;
import top.UnitBase;

public class ServerVisitorTc extends UnitBase {

    @Test(expected = ArgumentException.class)
    public void testVisit() throws ZeroException {
        final Visitor<HttpServerOptions> visitor
                = Instance.singleton(ServerVisitor.class);
        visitor.visit("");
    }

    @Test
    public void testVisitOk() throws ZeroException {
        final Visitor<HttpServerOptions> visitor
                = Instance.singleton(ServerVisitor.class);
        visitor.visit();
    }
}
