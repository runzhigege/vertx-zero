package cn.vertxup.erp.api;

import io.vertx.tp.erp.cv.ErpMsg;
import io.vertx.tp.erp.refine.Er;
import io.vertx.tp.erp.service.DeptStub;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.tp.erp.cv.Addr;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.Queue;
import io.vertx.up.log.Annal;

import javax.inject.Inject;

@Queue
public class DeptActor {

    private static final Annal LOGGER = Annal.get(DeptActor.class);

    @Inject
    private transient DeptStub deptStub;

    @Address(Addr.Dept.DEPT_SIGMA)
    public Future<JsonArray> fetchDepts(final String sigma) {
        Er.infoWorker(LOGGER, ErpMsg.DEPT_INFO, sigma);
        return deptStub.fetchDepts(sigma);
    }
}
