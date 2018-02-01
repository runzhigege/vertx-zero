# D10028 - Zero JSR330, @Inject Multi Implementation

From previous tutorials, zero system support following three injection codes.

* [x] Simple Java Object Injection
* [x] One interface and one java implementation object Injection
* [x] One interface and multi java implementation objects Injection

This chapter will describe the last mode that the structure should be one interface with multi implementation objects. The workflow of this example should be:

```shell
Request -> Agent -> @Address ( Sender ) -> 
    EventBus -> 
        @Address ( Consumer ) -> Worker 
                                    -> Stub -> ServiceA ( Service B ) -> Response
```

Demo projects:

* **Standalone - 6083**: `up-rhea`

## 1. Source Code

### 1.1. Sender

```java
package up.god.micro.inject;

import io.vertx.core.json.JsonObject;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.EndPoint;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;

@EndPoint
@Path("/api")
public class MultiActor {

    @Path("inject/multi")
    @PUT
    @Address("ZERO://INJECT/MULTI")
    public JsonObject sayInject(final JsonObject data
    ) {
        return new JsonObject()
                .put("age", 33)
                .mergeIn(data);
    }
}
```

### 1.2. Consumer

```java
package up.god.micro.inject;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.Qualifier;
import io.vertx.up.annotations.Queue;

import javax.inject.Inject;

@Queue
public class MultiWorker {

    @Inject
    @Qualifier("ServiceB")
    private transient MultiStub stub;

    @Address("ZERO://INJECT/MULTI")
    public Future<JsonObject> process(final JsonObject user) {
        final JsonObject processed = this.stub.getData(user);
        return Future.succeededFuture(processed);
    }
}
```

### 1.3. Stub \( Service Interface \)

```java
package up.god.micro.inject;

import io.vertx.core.json.JsonObject;

public interface MultiStub {

    JsonObject getData(JsonObject input);
}
```

### 1.4. Service \( Service A Implementation \)

```java
package up.god.micro.inject;

import io.vertx.core.json.JsonObject;

import javax.inject.Named;

@Named("ServiceA")
public class MultiServiceA implements MultiStub {
    @Override
    public JsonObject getData(final JsonObject input) {
        input.put("className", getClass().getName());
        return input;
    }
}
```

### 1.5. Service \( Service B Implementation \)

```java
package up.god.micro.inject;

import io.vertx.core.json.JsonObject;

import javax.inject.Named;

@Named("ServiceB")
public class MultiServiceB implements MultiStub {
    @Override
    public JsonObject getData(final JsonObject input) {
        input.put("className", getClass().getName());
        return input;
    }
}
```



