package up.god.jooq;

import io.vertx.ext.unit.TestContext;
import org.junit.Test;
import up.god.domain.tables.pojos.SysTabular;

/**
 * insertAsync
 */
public class AsyncInsertTc extends JooqTc {

    @Test
    public void testInsert(final TestContext context) {
        final SysTabular tabular = this.getTabular("pojo.json");
        this.async(context, () -> this.getDao().insertAsync(tabular), actual -> {
            context.assertEquals(tabular, actual);
            // Async Finished
            this.getDao().deleteById(actual.getPkId());
            // Sync
        });
    }
}
