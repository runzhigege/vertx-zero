# D10029 - JSR303, @NotNull

From this chapter, we'll ignore the console output because in previous tutorials, you have known most of zero output logs. In many business scenarios, you need following features in your system:

* Validator: When the data you got from client is invalid, you must provide rejection response.
* Filter: You could do some conversion in your request.
* Listener: You need some background schedulers to monitor the system status.

From this chapter we'll focus on [JSR303, Bean Validation](https://jcp.org/en/jsr/detail?id=303) part, zero has used Hibernate Validator to support JSR303 validation, except this specification, zero system created another advanced validation feature to verify your json data format, it's common used in different Body Request here.

Demo projects:

* **Standalone - 6083**: `up-rhea`

**Rules**:

1. JSR303 is only supported in Agent component in zero system, it means that after you have send the message to event bus, the JSR303 will be effectiveness.
2. When you write the code with the Interface Style \( Will introduce in forward tutorials \), JSR303 will not support this kind of situation.
3. For @BodyParam, it also impact Agent component only, but could support Interface Style instead of JSR303 and could provide more useful validations.

## 1. Source Code

```java
package up.god.micro.validation;

import io.vertx.up.annotations.EndPoint;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@EndPoint
@Path("/api/jsr303/")
public class NotNullActor {

    @GET
    @Path("/notnull/query")
    public String testValid(@PathParam("name") final String name) {
        return "Hello, " + name;
    }
}
```



