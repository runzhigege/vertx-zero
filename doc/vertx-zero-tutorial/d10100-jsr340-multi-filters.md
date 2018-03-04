# D10100 - JSR340 Multi Filters

This tutorial we'll modify the demo that has been described in previous tutorial to write multi filters in zero system, in this kind of situation we could manage all the filters before your API, this feature is more useful and powerful.

Demo projects:

* **Standalone - 6084**: `up-tethys`

## 1. Source Code

### 1.1.API

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

### 1.2.Consumer

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
        final String key1 = envelop.context("key1", String.class);
        return Future.succeededFuture(new JsonObject().put("key", key).put("key1", key1));
    }
}
```

### 1.3 Filters





