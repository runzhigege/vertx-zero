# D10099 - JSR340 Filter in Worker

This tutorial will continue to introduce how to use JSR340 @WebFilter in zero system. Here are some points that should be mentioned because of some limitation between JSR340 and Vert.x:

* All the filters must implement `io.vertx.up.web.filter.Filter` instead of JSR340;
* We recommend developers extend from `io.vertx.up.web.filter.HttpFilter` directly because there are some abstract implementations in parent filters.
* Please be careful of the signature of `doFilter` especially focus on the parameter types.

Demo projects:

* **Standalone - 6084**: `up-tethys`

This tutorial will describe the usage of Filters in Consumer \( Worker \).

## 1. Source Code

### 1.1. Api

```java
package up.god.micro.filter;

import io.vertx.core.json.JsonObject;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.EndPoint;

import javax.ws.rs.BodyParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@EndPoint
@Path("/api")
public interface FilterApi {

    @POST
    @Path("/jsr340/worker")
    @Address("ZERO://JSR340/WORKER")
    JsonObject filter(@BodyParam final JsonObject data);
}
```

### 1.2. Consumer

```java
package up.god.micro.filter;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.Queue;
import io.vertx.up.atom.Envelop;

@Queue
public class FilterWorker {

    @Address("ZERO://JSR340/WORKER")
    public Future<JsonObject> work(final Envelop envelop) {
        final String key = envelop.context("key", String.class);
        return Future.succeededFuture(new JsonObject().put("key", key));
    }
}
```



