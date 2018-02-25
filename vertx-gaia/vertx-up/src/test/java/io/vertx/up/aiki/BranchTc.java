package io.vertx.up.aiki;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
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

    @Test
    public void testToFilters() {
        final TestFilter filter = new TestFilter();
        filter.setEmail("lang.yu@hpe.com");
        filter.setName("lang.yu");
        final JsonObject data = Ux.toFilters(new String[]{
                "S_EMAIL", "S_NAME"
        }, filter::getEmail, filter::getName);
        System.out.println(data);
    }
}

class TestFilter {
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    private String email;
}