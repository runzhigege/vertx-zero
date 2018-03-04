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



