/*
 * This file is generated by jOOQ.
*/
package cn.vertxup.erp.domain.tables.daos;


import cn.vertxup.erp.domain.tables.EEmployee;
import cn.vertxup.erp.domain.tables.records.EEmployeeRecord;

import io.github.jklingsporn.vertx.jooq.future.VertxDAO;

import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;


import java.util.concurrent.CompletableFuture;
import io.github.jklingsporn.vertx.jooq.future.util.FutureTool;
/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.8"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class EEmployeeDao extends DAOImpl<EEmployeeRecord, cn.vertxup.erp.domain.tables.pojos.EEmployee, String> implements VertxDAO<cn.vertxup.erp.domain.tables.records.EEmployeeRecord,cn.vertxup.erp.domain.tables.pojos.EEmployee,java.lang.String> {

    /**
     * Create a new EEmployeeDao without any configuration
     */
    public EEmployeeDao() {
        super(EEmployee.E_EMPLOYEE, cn.vertxup.erp.domain.tables.pojos.EEmployee.class);
    }

    /**
     * Create a new EEmployeeDao with an attached configuration
     */
    public EEmployeeDao(Configuration configuration) {
        super(EEmployee.E_EMPLOYEE, cn.vertxup.erp.domain.tables.pojos.EEmployee.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getId(cn.vertxup.erp.domain.tables.pojos.EEmployee object) {
        return object.getKey();
    }

    /**
     * Fetch records that have <code>KEY IN (values)</code>
     */
    public List<cn.vertxup.erp.domain.tables.pojos.EEmployee> fetchByKey(String... values) {
        return fetch(EEmployee.E_EMPLOYEE.KEY, values);
    }

    /**
     * Fetch a unique record that has <code>KEY = value</code>
     */
    public cn.vertxup.erp.domain.tables.pojos.EEmployee fetchOneByKey(String value) {
        return fetchOne(EEmployee.E_EMPLOYEE.KEY, value);
    }

    /**
     * Fetch records that have <code>COMPANY_ID IN (values)</code>
     */
    public List<cn.vertxup.erp.domain.tables.pojos.EEmployee> fetchByCompanyId(String... values) {
        return fetch(EEmployee.E_EMPLOYEE.COMPANY_ID, values);
    }

    /**
     * Fetch records that have <code>DEPT_ID IN (values)</code>
     */
    public List<cn.vertxup.erp.domain.tables.pojos.EEmployee> fetchByDeptId(String... values) {
        return fetch(EEmployee.E_EMPLOYEE.DEPT_ID, values);
    }

    /**
     * Fetch records that have <code>IDENTITY_ID IN (values)</code>
     */
    public List<cn.vertxup.erp.domain.tables.pojos.EEmployee> fetchByIdentityId(String... values) {
        return fetch(EEmployee.E_EMPLOYEE.IDENTITY_ID, values);
    }

    /**
     * Fetch records that have <code>WORK_NUMBER IN (values)</code>
     */
    public List<cn.vertxup.erp.domain.tables.pojos.EEmployee> fetchByWorkNumber(String... values) {
        return fetch(EEmployee.E_EMPLOYEE.WORK_NUMBER, values);
    }

    /**
     * Fetch records that have <code>WORK_TITLE IN (values)</code>
     */
    public List<cn.vertxup.erp.domain.tables.pojos.EEmployee> fetchByWorkTitle(String... values) {
        return fetch(EEmployee.E_EMPLOYEE.WORK_TITLE, values);
    }

    /**
     * Fetch records that have <code>WORK_EMAIL IN (values)</code>
     */
    public List<cn.vertxup.erp.domain.tables.pojos.EEmployee> fetchByWorkEmail(String... values) {
        return fetch(EEmployee.E_EMPLOYEE.WORK_EMAIL, values);
    }

    /**
     * Fetch records that have <code>WORK_LOCATION IN (values)</code>
     */
    public List<cn.vertxup.erp.domain.tables.pojos.EEmployee> fetchByWorkLocation(String... values) {
        return fetch(EEmployee.E_EMPLOYEE.WORK_LOCATION, values);
    }

    /**
     * Fetch records that have <code>WORK_PHONE IN (values)</code>
     */
    public List<cn.vertxup.erp.domain.tables.pojos.EEmployee> fetchByWorkPhone(String... values) {
        return fetch(EEmployee.E_EMPLOYEE.WORK_PHONE, values);
    }

    /**
     * Fetch records that have <code>TYPE IN (values)</code>
     */
    public List<cn.vertxup.erp.domain.tables.pojos.EEmployee> fetchByType(String... values) {
        return fetch(EEmployee.E_EMPLOYEE.TYPE, values);
    }

    /**
     * Fetch records that have <code>METADATA IN (values)</code>
     */
    public List<cn.vertxup.erp.domain.tables.pojos.EEmployee> fetchByMetadata(String... values) {
        return fetch(EEmployee.E_EMPLOYEE.METADATA, values);
    }

    /**
     * Fetch records that have <code>ACTIVE IN (values)</code>
     */
    public List<cn.vertxup.erp.domain.tables.pojos.EEmployee> fetchByActive(Boolean... values) {
        return fetch(EEmployee.E_EMPLOYEE.ACTIVE, values);
    }

    /**
     * Fetch records that have <code>SIGMA IN (values)</code>
     */
    public List<cn.vertxup.erp.domain.tables.pojos.EEmployee> fetchBySigma(String... values) {
        return fetch(EEmployee.E_EMPLOYEE.SIGMA, values);
    }

    /**
     * Fetch records that have <code>LANGUAGE IN (values)</code>
     */
    public List<cn.vertxup.erp.domain.tables.pojos.EEmployee> fetchByLanguage(String... values) {
        return fetch(EEmployee.E_EMPLOYEE.LANGUAGE, values);
    }

    /**
     * Fetch records that have <code>CREATED_AT IN (values)</code>
     */
    public List<cn.vertxup.erp.domain.tables.pojos.EEmployee> fetchByCreatedAt(LocalDateTime... values) {
        return fetch(EEmployee.E_EMPLOYEE.CREATED_AT, values);
    }

    /**
     * Fetch records that have <code>CREATED_BY IN (values)</code>
     */
    public List<cn.vertxup.erp.domain.tables.pojos.EEmployee> fetchByCreatedBy(String... values) {
        return fetch(EEmployee.E_EMPLOYEE.CREATED_BY, values);
    }

    /**
     * Fetch records that have <code>UPDATED_AT IN (values)</code>
     */
    public List<cn.vertxup.erp.domain.tables.pojos.EEmployee> fetchByUpdatedAt(LocalDateTime... values) {
        return fetch(EEmployee.E_EMPLOYEE.UPDATED_AT, values);
    }

    /**
     * Fetch records that have <code>UPDATED_BY IN (values)</code>
     */
    public List<cn.vertxup.erp.domain.tables.pojos.EEmployee> fetchByUpdatedBy(String... values) {
        return fetch(EEmployee.E_EMPLOYEE.UPDATED_BY, values);
    }

    /**
     * Fetch records that have <code>KEY IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.erp.domain.tables.pojos.EEmployee>> fetchByKeyAsync(List<String> values) {
        return fetchAsync(EEmployee.E_EMPLOYEE.KEY,values);
    }

    /**
     * Fetch a unique record that has <code>KEY = value</code> asynchronously
     */
    public CompletableFuture<cn.vertxup.erp.domain.tables.pojos.EEmployee> fetchOneByKeyAsync(String value) {
        return FutureTool.executeBlocking(h->h.complete(fetchOneByKey(value)),vertx());
    }

    /**
     * Fetch records that have <code>COMPANY_ID IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.erp.domain.tables.pojos.EEmployee>> fetchByCompanyIdAsync(List<String> values) {
        return fetchAsync(EEmployee.E_EMPLOYEE.COMPANY_ID,values);
    }

    /**
     * Fetch records that have <code>DEPT_ID IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.erp.domain.tables.pojos.EEmployee>> fetchByDeptIdAsync(List<String> values) {
        return fetchAsync(EEmployee.E_EMPLOYEE.DEPT_ID,values);
    }

    /**
     * Fetch records that have <code>IDENTITY_ID IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.erp.domain.tables.pojos.EEmployee>> fetchByIdentityIdAsync(List<String> values) {
        return fetchAsync(EEmployee.E_EMPLOYEE.IDENTITY_ID,values);
    }

    /**
     * Fetch records that have <code>WORK_NUMBER IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.erp.domain.tables.pojos.EEmployee>> fetchByWorkNumberAsync(List<String> values) {
        return fetchAsync(EEmployee.E_EMPLOYEE.WORK_NUMBER,values);
    }

    /**
     * Fetch records that have <code>WORK_TITLE IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.erp.domain.tables.pojos.EEmployee>> fetchByWorkTitleAsync(List<String> values) {
        return fetchAsync(EEmployee.E_EMPLOYEE.WORK_TITLE,values);
    }

    /**
     * Fetch records that have <code>WORK_EMAIL IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.erp.domain.tables.pojos.EEmployee>> fetchByWorkEmailAsync(List<String> values) {
        return fetchAsync(EEmployee.E_EMPLOYEE.WORK_EMAIL,values);
    }

    /**
     * Fetch records that have <code>WORK_LOCATION IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.erp.domain.tables.pojos.EEmployee>> fetchByWorkLocationAsync(List<String> values) {
        return fetchAsync(EEmployee.E_EMPLOYEE.WORK_LOCATION,values);
    }

    /**
     * Fetch records that have <code>WORK_PHONE IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.erp.domain.tables.pojos.EEmployee>> fetchByWorkPhoneAsync(List<String> values) {
        return fetchAsync(EEmployee.E_EMPLOYEE.WORK_PHONE,values);
    }

    /**
     * Fetch records that have <code>TYPE IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.erp.domain.tables.pojos.EEmployee>> fetchByTypeAsync(List<String> values) {
        return fetchAsync(EEmployee.E_EMPLOYEE.TYPE,values);
    }

    /**
     * Fetch records that have <code>METADATA IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.erp.domain.tables.pojos.EEmployee>> fetchByMetadataAsync(List<String> values) {
        return fetchAsync(EEmployee.E_EMPLOYEE.METADATA,values);
    }

    /**
     * Fetch records that have <code>ACTIVE IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.erp.domain.tables.pojos.EEmployee>> fetchByActiveAsync(List<Boolean> values) {
        return fetchAsync(EEmployee.E_EMPLOYEE.ACTIVE,values);
    }

    /**
     * Fetch records that have <code>SIGMA IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.erp.domain.tables.pojos.EEmployee>> fetchBySigmaAsync(List<String> values) {
        return fetchAsync(EEmployee.E_EMPLOYEE.SIGMA,values);
    }

    /**
     * Fetch records that have <code>LANGUAGE IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.erp.domain.tables.pojos.EEmployee>> fetchByLanguageAsync(List<String> values) {
        return fetchAsync(EEmployee.E_EMPLOYEE.LANGUAGE,values);
    }

    /**
     * Fetch records that have <code>CREATED_AT IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.erp.domain.tables.pojos.EEmployee>> fetchByCreatedAtAsync(List<LocalDateTime> values) {
        return fetchAsync(EEmployee.E_EMPLOYEE.CREATED_AT,values);
    }

    /**
     * Fetch records that have <code>CREATED_BY IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.erp.domain.tables.pojos.EEmployee>> fetchByCreatedByAsync(List<String> values) {
        return fetchAsync(EEmployee.E_EMPLOYEE.CREATED_BY,values);
    }

    /**
     * Fetch records that have <code>UPDATED_AT IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.erp.domain.tables.pojos.EEmployee>> fetchByUpdatedAtAsync(List<LocalDateTime> values) {
        return fetchAsync(EEmployee.E_EMPLOYEE.UPDATED_AT,values);
    }

    /**
     * Fetch records that have <code>UPDATED_BY IN (values)</code> asynchronously
     */
    public CompletableFuture<List<cn.vertxup.erp.domain.tables.pojos.EEmployee>> fetchByUpdatedByAsync(List<String> values) {
        return fetchAsync(EEmployee.E_EMPLOYEE.UPDATED_BY,values);
    }

    private io.vertx.core.Vertx vertx;

    @Override
    public void setVertx(io.vertx.core.Vertx vertx) {
        this.vertx = vertx;
    }

    @Override
    public io.vertx.core.Vertx vertx() {
        return this.vertx;
    }

}
