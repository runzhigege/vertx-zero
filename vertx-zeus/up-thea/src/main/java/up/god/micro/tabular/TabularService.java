package up.god.micro.tabular;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.up.aiki.Ux;
import up.god.domain.tables.daos.SysTabularDao;
import up.god.domain.tables.pojos.SysTabular;

public class TabularService implements TabularStub {

    @Override
    public Future<JsonObject> fetchOne(final Long id) {
        return Ux.Jooq.on(SysTabularDao.class)
                .<SysTabular>findByIdAsync(id)
                .compose(item -> Ux.thenJsonOne(item, "tabular"));
    }

    @Override
    public Future<JsonObject> create(final JsonObject data) {
        return null;
    }

    @Override
    public Future<JsonObject> update(final Long id, final JsonObject data) {
        return null;
    }

    @Override
    public Future<JsonObject> delete(final Long id) {
        return null;
    }
}
