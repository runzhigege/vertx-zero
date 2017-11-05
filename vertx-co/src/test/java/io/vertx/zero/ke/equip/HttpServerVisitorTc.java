package io.vertx.zero.ke.equip;

import com.vie.hors.ZeroException;
import com.vie.hors.ke.ArgumentException;
import com.vie.util.Instance;
import io.vertx.core.http.HttpServerOptions;
import org.junit.Test;
import top.UnitBase;

public class HttpServerVisitorTc extends UnitBase {

    @Test(expected = ArgumentException.class)
    public void testVisit() throws ZeroException {
        final ServerVisitor<HttpServerOptions> visitor
                = Instance.singleton(HttpServerVisitor.class);
        visitor.visit("");
    }

    @Test
    public void testVisitOk() throws ZeroException {
        final ServerVisitor<HttpServerOptions> visitor
                = Instance.singleton(HttpServerVisitor.class);
        visitor.visit();
    }
}
