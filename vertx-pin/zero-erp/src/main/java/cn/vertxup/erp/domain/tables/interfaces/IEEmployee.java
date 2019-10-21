/*
 * This file is generated by jOOQ.
*/
package cn.vertxup.erp.domain.tables.interfaces;


import java.io.Serializable;
import java.time.LocalDateTime;

import javax.annotation.Generated;


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
public interface IEEmployee extends Serializable {

    /**
     * Setter for <code>DB_ETERNAL.E_EMPLOYEE.KEY</code>. 「key」- 员工主键
     */
    public IEEmployee setKey(String value);

    /**
     * Getter for <code>DB_ETERNAL.E_EMPLOYEE.KEY</code>. 「key」- 员工主键
     */
    public String getKey();

    /**
     * Setter for <code>DB_ETERNAL.E_EMPLOYEE.COMPANY_ID</code>. 「companyId」- 所属公司
     */
    public IEEmployee setCompanyId(String value);

    /**
     * Getter for <code>DB_ETERNAL.E_EMPLOYEE.COMPANY_ID</code>. 「companyId」- 所属公司
     */
    public String getCompanyId();

    /**
     * Setter for <code>DB_ETERNAL.E_EMPLOYEE.DEPT_ID</code>. 「deptId」- 所属部门
     */
    public IEEmployee setDeptId(String value);

    /**
     * Getter for <code>DB_ETERNAL.E_EMPLOYEE.DEPT_ID</code>. 「deptId」- 所属部门
     */
    public String getDeptId();

    /**
     * Setter for <code>DB_ETERNAL.E_EMPLOYEE.TEAM_ID</code>. 「teamId」- 所属组
     */
    public IEEmployee setTeamId(String value);

    /**
     * Getter for <code>DB_ETERNAL.E_EMPLOYEE.TEAM_ID</code>. 「teamId」- 所属组
     */
    public String getTeamId();

    /**
     * Setter for <code>DB_ETERNAL.E_EMPLOYEE.IDENTITY_ID</code>. 「identityId」- 身份关联ID
     */
    public IEEmployee setIdentityId(String value);

    /**
     * Getter for <code>DB_ETERNAL.E_EMPLOYEE.IDENTITY_ID</code>. 「identityId」- 身份关联ID
     */
    public String getIdentityId();

    /**
     * Setter for <code>DB_ETERNAL.E_EMPLOYEE.WORK_CN_NAME</code>. 「workCnName」- 中文名
     */
    public IEEmployee setWorkCnName(String value);

    /**
     * Getter for <code>DB_ETERNAL.E_EMPLOYEE.WORK_CN_NAME</code>. 「workCnName」- 中文名
     */
    public String getWorkCnName();

    /**
     * Setter for <code>DB_ETERNAL.E_EMPLOYEE.WORK_EN_NAME</code>. 「workEnName」- 英文名
     */
    public IEEmployee setWorkEnName(String value);

    /**
     * Getter for <code>DB_ETERNAL.E_EMPLOYEE.WORK_EN_NAME</code>. 「workEnName」- 英文名
     */
    public String getWorkEnName();

    /**
     * Setter for <code>DB_ETERNAL.E_EMPLOYEE.WORK_NUMBER</code>. 「workNumber」- 工号
     */
    public IEEmployee setWorkNumber(String value);

    /**
     * Getter for <code>DB_ETERNAL.E_EMPLOYEE.WORK_NUMBER</code>. 「workNumber」- 工号
     */
    public String getWorkNumber();

    /**
     * Setter for <code>DB_ETERNAL.E_EMPLOYEE.WORK_TITLE</code>. 「workTitle」- 头衔
     */
    public IEEmployee setWorkTitle(String value);

    /**
     * Getter for <code>DB_ETERNAL.E_EMPLOYEE.WORK_TITLE</code>. 「workTitle」- 头衔
     */
    public String getWorkTitle();

    /**
     * Setter for <code>DB_ETERNAL.E_EMPLOYEE.WORK_EMAIL</code>. 「workEmail」- 办公邮箱
     */
    public IEEmployee setWorkEmail(String value);

    /**
     * Getter for <code>DB_ETERNAL.E_EMPLOYEE.WORK_EMAIL</code>. 「workEmail」- 办公邮箱
     */
    public String getWorkEmail();

    /**
     * Setter for <code>DB_ETERNAL.E_EMPLOYEE.WORK_LOCATION</code>. 「workLocation」- 办公地点
     */
    public IEEmployee setWorkLocation(String value);

    /**
     * Getter for <code>DB_ETERNAL.E_EMPLOYEE.WORK_LOCATION</code>. 「workLocation」- 办公地点
     */
    public String getWorkLocation();

    /**
     * Setter for <code>DB_ETERNAL.E_EMPLOYEE.WORK_PHONE</code>. 「workPhone」- 办公电话
     */
    public IEEmployee setWorkPhone(String value);

    /**
     * Getter for <code>DB_ETERNAL.E_EMPLOYEE.WORK_PHONE</code>. 「workPhone」- 办公电话
     */
    public String getWorkPhone();

    /**
     * Setter for <code>DB_ETERNAL.E_EMPLOYEE.WORK_EXTENSION</code>. 「workExtension」- 分机号
     */
    public IEEmployee setWorkExtension(String value);

    /**
     * Getter for <code>DB_ETERNAL.E_EMPLOYEE.WORK_EXTENSION</code>. 「workExtension」- 分机号
     */
    public String getWorkExtension();

    /**
     * Setter for <code>DB_ETERNAL.E_EMPLOYEE.WORK_MOBILE</code>. 「workMobile」- 办公用手机
     */
    public IEEmployee setWorkMobile(String value);

    /**
     * Getter for <code>DB_ETERNAL.E_EMPLOYEE.WORK_MOBILE</code>. 「workMobile」- 办公用手机
     */
    public String getWorkMobile();

    /**
     * Setter for <code>DB_ETERNAL.E_EMPLOYEE.TYPE</code>. 「type」- 员工分类
     */
    public IEEmployee setType(String value);

    /**
     * Getter for <code>DB_ETERNAL.E_EMPLOYEE.TYPE</code>. 「type」- 员工分类
     */
    public String getType();

    /**
     * Setter for <code>DB_ETERNAL.E_EMPLOYEE.METADATA</code>. 「metadata」- 附加配置
     */
    public IEEmployee setMetadata(String value);

    /**
     * Getter for <code>DB_ETERNAL.E_EMPLOYEE.METADATA</code>. 「metadata」- 附加配置
     */
    public String getMetadata();

    /**
     * Setter for <code>DB_ETERNAL.E_EMPLOYEE.ACTIVE</code>. 「active」- 是否启用
     */
    public IEEmployee setActive(Boolean value);

    /**
     * Getter for <code>DB_ETERNAL.E_EMPLOYEE.ACTIVE</code>. 「active」- 是否启用
     */
    public Boolean getActive();

    /**
     * Setter for <code>DB_ETERNAL.E_EMPLOYEE.SIGMA</code>. 「sigma」- 统一标识（公司所属应用）
     */
    public IEEmployee setSigma(String value);

    /**
     * Getter for <code>DB_ETERNAL.E_EMPLOYEE.SIGMA</code>. 「sigma」- 统一标识（公司所属应用）
     */
    public String getSigma();

    /**
     * Setter for <code>DB_ETERNAL.E_EMPLOYEE.LANGUAGE</code>. 「language」- 使用的语言
     */
    public IEEmployee setLanguage(String value);

    /**
     * Getter for <code>DB_ETERNAL.E_EMPLOYEE.LANGUAGE</code>. 「language」- 使用的语言
     */
    public String getLanguage();

    /**
     * Setter for <code>DB_ETERNAL.E_EMPLOYEE.CREATED_AT</code>. 「createdAt」- 创建时间
     */
    public IEEmployee setCreatedAt(LocalDateTime value);

    /**
     * Getter for <code>DB_ETERNAL.E_EMPLOYEE.CREATED_AT</code>. 「createdAt」- 创建时间
     */
    public LocalDateTime getCreatedAt();

    /**
     * Setter for <code>DB_ETERNAL.E_EMPLOYEE.CREATED_BY</code>. 「createdBy」- 创建人
     */
    public IEEmployee setCreatedBy(String value);

    /**
     * Getter for <code>DB_ETERNAL.E_EMPLOYEE.CREATED_BY</code>. 「createdBy」- 创建人
     */
    public String getCreatedBy();

    /**
     * Setter for <code>DB_ETERNAL.E_EMPLOYEE.UPDATED_AT</code>. 「updatedAt」- 更新时间
     */
    public IEEmployee setUpdatedAt(LocalDateTime value);

    /**
     * Getter for <code>DB_ETERNAL.E_EMPLOYEE.UPDATED_AT</code>. 「updatedAt」- 更新时间
     */
    public LocalDateTime getUpdatedAt();

    /**
     * Setter for <code>DB_ETERNAL.E_EMPLOYEE.UPDATED_BY</code>. 「updatedBy」- 更新人
     */
    public IEEmployee setUpdatedBy(String value);

    /**
     * Getter for <code>DB_ETERNAL.E_EMPLOYEE.UPDATED_BY</code>. 「updatedBy」- 更新人
     */
    public String getUpdatedBy();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common interface IEEmployee
     */
    public void from(cn.vertxup.erp.domain.tables.interfaces.IEEmployee from);

    /**
     * Copy data into another generated Record/POJO implementing the common interface IEEmployee
     */
    public <E extends cn.vertxup.erp.domain.tables.interfaces.IEEmployee> E into(E into);

    default IEEmployee fromJson(io.vertx.core.json.JsonObject json) {
        setKey(json.getString("KEY"));
        setCompanyId(json.getString("COMPANY_ID"));
        setDeptId(json.getString("DEPT_ID"));
        setTeamId(json.getString("TEAM_ID"));
        setIdentityId(json.getString("IDENTITY_ID"));
        setWorkCnName(json.getString("WORK_CN_NAME"));
        setWorkEnName(json.getString("WORK_EN_NAME"));
        setWorkNumber(json.getString("WORK_NUMBER"));
        setWorkTitle(json.getString("WORK_TITLE"));
        setWorkEmail(json.getString("WORK_EMAIL"));
        setWorkLocation(json.getString("WORK_LOCATION"));
        setWorkPhone(json.getString("WORK_PHONE"));
        setWorkExtension(json.getString("WORK_EXTENSION"));
        setWorkMobile(json.getString("WORK_MOBILE"));
        setType(json.getString("TYPE"));
        setMetadata(json.getString("METADATA"));
        setActive(json.getBoolean("ACTIVE"));
        setSigma(json.getString("SIGMA"));
        setLanguage(json.getString("LANGUAGE"));
        // Omitting unrecognized type java.time.LocalDateTime for column CREATED_AT!
        setCreatedBy(json.getString("CREATED_BY"));
        // Omitting unrecognized type java.time.LocalDateTime for column UPDATED_AT!
        setUpdatedBy(json.getString("UPDATED_BY"));
        return this;
    }


    default io.vertx.core.json.JsonObject toJson() {
        io.vertx.core.json.JsonObject json = new io.vertx.core.json.JsonObject();
        json.put("KEY",getKey());
        json.put("COMPANY_ID",getCompanyId());
        json.put("DEPT_ID",getDeptId());
        json.put("TEAM_ID",getTeamId());
        json.put("IDENTITY_ID",getIdentityId());
        json.put("WORK_CN_NAME",getWorkCnName());
        json.put("WORK_EN_NAME",getWorkEnName());
        json.put("WORK_NUMBER",getWorkNumber());
        json.put("WORK_TITLE",getWorkTitle());
        json.put("WORK_EMAIL",getWorkEmail());
        json.put("WORK_LOCATION",getWorkLocation());
        json.put("WORK_PHONE",getWorkPhone());
        json.put("WORK_EXTENSION",getWorkExtension());
        json.put("WORK_MOBILE",getWorkMobile());
        json.put("TYPE",getType());
        json.put("METADATA",getMetadata());
        json.put("ACTIVE",getActive());
        json.put("SIGMA",getSigma());
        json.put("LANGUAGE",getLanguage());
        // Omitting unrecognized type java.time.LocalDateTime for column CREATED_AT!
        json.put("CREATED_BY",getCreatedBy());
        // Omitting unrecognized type java.time.LocalDateTime for column UPDATED_AT!
        json.put("UPDATED_BY",getUpdatedBy());
        return json;
    }

}
