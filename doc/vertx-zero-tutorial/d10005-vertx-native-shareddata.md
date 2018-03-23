# D10105 - Vert.x Native, SharedData

Actually, shared client is not native client, but we wrapper a concept in vert.x named **SharedData** , this client used this concept provide following features.

* Temporary storage of Map to store the data such as verification code etc.
* Temporary storage of Map to store the data that will be used once.

## 1. Configuration

This chapter is duplicated with [D10106 - Configuration, vertx-tp.yml](/doc/vertx-zero-tutorial/d10106-configuration-vertx-tpyml.md), it's pre-condition to use SharedClient.

### 1.1. vertx.yml

In major configuration file, you must extend to `vertx-tp.yml` file to enable this configuration.

```yaml
zero:
  lime: mongo,readible,secure,tp
  vertx:
    instance:
    - name: vx-zero
      options:
        # Fix block 2000 limit issue.
        maxEventLoopExecuteTime: 30000000000
```

### 1.2. vertx-tp.yml

This file must contain shared data information, actually there is only one configuration node named `shared`，if you want to enable this feature you can set as following:

```yaml
shared:
  config:
    async: true
```

### 1.3. vertx-inject.xml

The last configuration for shared data usage is that you must set `inject` in your configuration:

```yaml
shared: io.vertx.up.plugin.shared.MapInfix
```

Once you have finished above three configuration, the shared data will be enabled.

## 2. Source Code

```java
public class LoginService implements LoginStub{

    @Plugin
    private transient SharedClient<String, String> sharedClient;


}
```



