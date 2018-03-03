# D10099 - JSR340 Filter in Worker

This tutorial will continue to introduce how to use JSR340 @WebFilter in zero system. Here are some points that should be mentioned because of some limitation between JSR340 and Vert.x:

* All the filters must implement `io.vertx.up.web.filter.Filter` instead of JSR340;
* We recommend developers extend from `io.vertx.up.web.filter.HttpFilter` directly because there are some abstract implementations in parent filters.
* 


