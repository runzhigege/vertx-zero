package io.vertx.up.aiki;

import io.vertx.core.Future;
import org.junit.Test;

public class BranchTc {
    @Test
    public void testBranch() {
        final Future<String> result = Ux.match(
                () -> Ux.fork(
                        () -> Ux.on(this.getClass()).on("[ Log ] Fork -> {0}").info("Lang"),
                        () -> Future.succeededFuture("Fork")),
                Ux.branch(true,
                        () -> Ux.on(this.getClass()).on("[ Log ] Branch -> {0}").info("Lang"),
                        () -> Future.succeededFuture("Branch"))
        );
        result.setHandler(System.out::println);
    }
}
