package io.vertx.zero.atom;

import io.vertx.core.json.JsonObject;
import io.vertx.quiz.ZeroBase;
import io.vertx.up.epic.io.IO;
import org.junit.Test;

public class MirrorTc extends ZeroBase {

    @Test
    public void testFromRule() {
        final JsonObject input = IO.getJObject(this.getFile("user.json"));
        final JsonObject user = Mirror.create(this.getClass())
                .mount("user").connect(input).from().result();
        System.out.println(user);
    }

    @Test
    public void testToRule() {
        final JsonObject input = IO.getJObject(this.getFile("to.json"));
        final JsonObject user = Mirror.create(this.getClass())
                .mount("user").connect(input).to().result();
        System.out.println(user);
    }

    @Test
    public void testFromPojo() {
        final JsonObject input = IO.getJObject(this.getFile("user.json"));
        final Mirror mirror = Mirror.create(this.getClass())
                .mount("user").connect(input).from();
        final JsonObject user = mirror.result();
        final User entity = mirror.get();
        System.out.println(user);
        System.out.println(entity);
    }

    @Test
    public void testApply() {
        final JsonObject input = IO.getJObject(this.getFile("user.json"));
        final User user = new User();
        user.setAge(13);
        final Mirror mirror = Mirror.create(this.getClass())
                .mount("user").connect(input).apply((old) -> old.substring(1, 2).toLowerCase() +
                        old.substring(2));
        System.out.println(mirror.json(user, false));
    }
}
