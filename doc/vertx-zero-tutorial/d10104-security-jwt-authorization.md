# D10104 - Security, Jwt Authorization

This tutorial we'll introduce the usage of `Jwt` Authorization in zero system, the workflow is as following:

**Generate Token** :

![](/doc/image/D10104-1.png)

**Verify Token:**

![](/doc/image/D10104-2.png)

Here are two workflow in zero system that developers could define:

* **Generate Token**: When the user send request to login api, you can call `store` method to generate token and send token back.
* **Verify Token**: Before zero system verified token, you can check with your own code logical here.

> In vert.x native JWT support, you must set your own code logical to process token, but in zero system, you could focus on two functions to process token only, zero has split the workflow and let developers process JWT more smartly.

Demo projects:

* **Standalone - 6084**: `up-tethys`

## 1. Source Code

### 1.1. Sender

```java
package up.god.micro.jwt;

import io.vertx.core.json.JsonObject;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.EndPoint;

import javax.ws.rs.BodyParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/api")
@EndPoint
public interface LoginActor {

    @POST
    @Path("/login")
    @Address("ZERO://QUEUE/LOGIN")
    JsonObject login(@BodyParam final JsonObject data);

    @POST
    @Path("/secure/jwt")
    @Address("ZERO://QUEUE/JWT")
    JsonObject secure(@BodyParam final JsonObject data);
}
```

### 1.2. Consumer

```java
package up.god.micro.jwt;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.up.aiki.Ux;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.Queue;
import io.vertx.up.atom.Envelop;
import io.vertx.up.secure.Security;

import javax.inject.Inject;

@Queue
public class LoginWorker {

    @Inject
    private transient Security security;

    @Address("ZERO://QUEUE/LOGIN")
    public Future<JsonObject> login(final Envelop envelop) {
        final JsonObject data = Ux.getJson(envelop);
        return Ux.Mongo.findOne("DB_USER", data)
                .compose(item -> this.security.store(item));
    }


    @Address("ZERO://QUEUE/JWT")
    public Future<JsonObject> secure(final Envelop envelop) {
        return Future.succeededFuture(new JsonObject());
    }
}
```

Be careful about above code that you should `inject` the Security interface.

### 1.3. Wall

```java
package up.wall;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.jwt.JWTAuthOptions;
import io.vertx.ext.web.handler.AuthHandler;
import io.vertx.up.aiki.Ux;
import io.vertx.up.annotations.Authenticate;
import io.vertx.up.annotations.Wall;
import io.vertx.up.secure.Security;
import io.vertx.up.secure.handler.JwtOstium;
import io.vertx.up.secure.provider.JwtAuth;

@Wall(value = "jwt", path = "/api/secure/*")
@SuppressWarnings("all")
public class JwtWall implements Security {

    @Authenticate
    public AuthHandler authenticate(final Vertx vertx,
                                    final JsonObject config) {
        return JwtOstium.create(JwtAuth.create(vertx, new JWTAuthOptions(config), this::verify));
    }

    @Override
    public Future<JsonObject> store(final JsonObject filter) {
        final JsonObject seed = new JsonObject()
                .put("username", filter.getString("username"))
                .put("id", filter.getString("_id"));
        final String token = Ux.Jwt.token(seed);
        return Ux.Mongo.findOneAndReplace("DB_USER", filter, "token", token);
    }

    @Override
    public Future<Boolean> verify(final JsonObject data) {
        final JsonObject extracted = Ux.Jwt.extract(data);
        final String token = data.getString("jwt");
        final JsonObject filters = new JsonObject()
                .put("_id", extracted.getString("id"))
                .put("token", token);
        return Ux.Mongo.existing("DB_USER", filters);
    }
}

```



