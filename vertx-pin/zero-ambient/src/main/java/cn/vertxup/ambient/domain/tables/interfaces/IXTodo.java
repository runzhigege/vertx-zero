/*
 * This file is generated by jOOQ.
*/
package cn.vertxup.ambient.domain.tables.interfaces;


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
public interface IXTodo extends Serializable {

    /**
     * Setter for <code>DB_ETERNAL.X_TODO.KEY</code>. 「key」- 待办主键
     */
    public IXTodo setKey(String value);

    /**
     * Getter for <code>DB_ETERNAL.X_TODO.KEY</code>. 「key」- 待办主键
     */
    public String getKey();

    /**
     * Setter for <code>DB_ETERNAL.X_TODO.SERIAL</code>. 「serial」- 待办编号，使用 X_NUMBER 生成
     */
    public IXTodo setSerial(String value);

    /**
     * Getter for <code>DB_ETERNAL.X_TODO.SERIAL</code>. 「serial」- 待办编号，使用 X_NUMBER 生成
     */
    public String getSerial();

    /**
     * Setter for <code>DB_ETERNAL.X_TODO.NAME</code>. 「name」- 待办名称（标题）
     */
    public IXTodo setName(String value);

    /**
     * Getter for <code>DB_ETERNAL.X_TODO.NAME</code>. 「name」- 待办名称（标题）
     */
    public String getName();

    /**
     * Setter for <code>DB_ETERNAL.X_TODO.CODE</code>. 「code」- 待办系统码
     */
    public IXTodo setCode(String value);

    /**
     * Getter for <code>DB_ETERNAL.X_TODO.CODE</code>. 「code」- 待办系统码
     */
    public String getCode();

    /**
     * Setter for <code>DB_ETERNAL.X_TODO.ICON</code>. 「icon」- 待办显示的图标
     */
    public IXTodo setIcon(String value);

    /**
     * Getter for <code>DB_ETERNAL.X_TODO.ICON</code>. 「icon」- 待办显示的图标
     */
    public String getIcon();

    /**
     * Setter for <code>DB_ETERNAL.X_TODO.STATUS</code>. 「status」- 待办状态
     */
    public IXTodo setStatus(String value);

    /**
     * Getter for <code>DB_ETERNAL.X_TODO.STATUS</code>. 「status」- 待办状态
     */
    public String getStatus();

    /**
     * Setter for <code>DB_ETERNAL.X_TODO.TYPE</code>. 「type」- 待办类型
     */
    public IXTodo setType(String value);

    /**
     * Getter for <code>DB_ETERNAL.X_TODO.TYPE</code>. 「type」- 待办类型
     */
    public String getType();

    /**
     * Setter for <code>DB_ETERNAL.X_TODO.EXPIRED_AT</code>. 「expiredAt」- 超时时间
     */
    public IXTodo setExpiredAt(LocalDateTime value);

    /**
     * Getter for <code>DB_ETERNAL.X_TODO.EXPIRED_AT</code>. 「expiredAt」- 超时时间
     */
    public LocalDateTime getExpiredAt();

    /**
     * Setter for <code>DB_ETERNAL.X_TODO.MODEL_ID</code>. 「modelId」- 组所关联的模型identifier，用于描述
     */
    public IXTodo setModelId(String value);

    /**
     * Getter for <code>DB_ETERNAL.X_TODO.MODEL_ID</code>. 「modelId」- 组所关联的模型identifier，用于描述
     */
    public String getModelId();

    /**
     * Setter for <code>DB_ETERNAL.X_TODO.MODEL_KEY</code>. 「modelKey」- 组所关联的模型记录ID，用于描述哪一个Model中的记录
     */
    public IXTodo setModelKey(String value);

    /**
     * Getter for <code>DB_ETERNAL.X_TODO.MODEL_KEY</code>. 「modelKey」- 组所关联的模型记录ID，用于描述哪一个Model中的记录
     */
    public String getModelKey();

    /**
     * Setter for <code>DB_ETERNAL.X_TODO.TO_GROUP</code>. 「toGroup」- 待办指定组
     */
    public IXTodo setToGroup(String value);

    /**
     * Getter for <code>DB_ETERNAL.X_TODO.TO_GROUP</code>. 「toGroup」- 待办指定组
     */
    public String getToGroup();

    /**
     * Setter for <code>DB_ETERNAL.X_TODO.TO_USER</code>. 「toUser」- 待办指定人
     */
    public IXTodo setToUser(String value);

    /**
     * Getter for <code>DB_ETERNAL.X_TODO.TO_USER</code>. 「toUser」- 待办指定人
     */
    public String getToUser();

    /**
     * Setter for <code>DB_ETERNAL.X_TODO.ASSIGNED_BY</code>. 「assignedBy」- 待办指派人
     */
    public IXTodo setAssignedBy(String value);

    /**
     * Getter for <code>DB_ETERNAL.X_TODO.ASSIGNED_BY</code>. 「assignedBy」- 待办指派人
     */
    public String getAssignedBy();

    /**
     * Setter for <code>DB_ETERNAL.X_TODO.ACCEPTED_BY</code>. 「acceptedBy」- 待办接收人
     */
    public IXTodo setAcceptedBy(String value);

    /**
     * Getter for <code>DB_ETERNAL.X_TODO.ACCEPTED_BY</code>. 「acceptedBy」- 待办接收人
     */
    public String getAcceptedBy();

    /**
     * Setter for <code>DB_ETERNAL.X_TODO.FINISHED_BY</code>. 「finishedBy」- 待办完成人
     */
    public IXTodo setFinishedBy(String value);

    /**
     * Getter for <code>DB_ETERNAL.X_TODO.FINISHED_BY</code>. 「finishedBy」- 待办完成人
     */
    public String getFinishedBy();

    /**
     * Setter for <code>DB_ETERNAL.X_TODO.TRACE_ID</code>. 「traceId」- 同一个流程的待办执行分组
     */
    public IXTodo setTraceId(String value);

    /**
     * Getter for <code>DB_ETERNAL.X_TODO.TRACE_ID</code>. 「traceId」- 同一个流程的待办执行分组
     */
    public String getTraceId();

    /**
     * Setter for <code>DB_ETERNAL.X_TODO.ACTIVE</code>. 「active」- 是否启用
     */
    public IXTodo setActive(Boolean value);

    /**
     * Getter for <code>DB_ETERNAL.X_TODO.ACTIVE</code>. 「active」- 是否启用
     */
    public Boolean getActive();

    /**
     * Setter for <code>DB_ETERNAL.X_TODO.SIGMA</code>. 「sigma」- 统一标识
     */
    public IXTodo setSigma(String value);

    /**
     * Getter for <code>DB_ETERNAL.X_TODO.SIGMA</code>. 「sigma」- 统一标识
     */
    public String getSigma();

    /**
     * Setter for <code>DB_ETERNAL.X_TODO.METADATA</code>. 「metadata」- 附加配置
     */
    public IXTodo setMetadata(String value);

    /**
     * Getter for <code>DB_ETERNAL.X_TODO.METADATA</code>. 「metadata」- 附加配置
     */
    public String getMetadata();

    /**
     * Setter for <code>DB_ETERNAL.X_TODO.LANGUAGE</code>. 「language」- 使用的语言
     */
    public IXTodo setLanguage(String value);

    /**
     * Getter for <code>DB_ETERNAL.X_TODO.LANGUAGE</code>. 「language」- 使用的语言
     */
    public String getLanguage();

    /**
     * Setter for <code>DB_ETERNAL.X_TODO.CREATED_AT</code>. 「createdAt」- 创建时间
     */
    public IXTodo setCreatedAt(LocalDateTime value);

    /**
     * Getter for <code>DB_ETERNAL.X_TODO.CREATED_AT</code>. 「createdAt」- 创建时间
     */
    public LocalDateTime getCreatedAt();

    /**
     * Setter for <code>DB_ETERNAL.X_TODO.CREATED_BY</code>. 「createdBy」- 创建人
     */
    public IXTodo setCreatedBy(String value);

    /**
     * Getter for <code>DB_ETERNAL.X_TODO.CREATED_BY</code>. 「createdBy」- 创建人
     */
    public String getCreatedBy();

    /**
     * Setter for <code>DB_ETERNAL.X_TODO.UPDATED_AT</code>. 「updatedAt」- 更新时间
     */
    public IXTodo setUpdatedAt(LocalDateTime value);

    /**
     * Getter for <code>DB_ETERNAL.X_TODO.UPDATED_AT</code>. 「updatedAt」- 更新时间
     */
    public LocalDateTime getUpdatedAt();

    /**
     * Setter for <code>DB_ETERNAL.X_TODO.UPDATED_BY</code>. 「updatedBy」- 更新人
     */
    public IXTodo setUpdatedBy(String value);

    /**
     * Getter for <code>DB_ETERNAL.X_TODO.UPDATED_BY</code>. 「updatedBy」- 更新人
     */
    public String getUpdatedBy();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common interface IXTodo
     */
    public void from(cn.vertxup.ambient.domain.tables.interfaces.IXTodo from);

    /**
     * Copy data into another generated Record/POJO implementing the common interface IXTodo
     */
    public <E extends cn.vertxup.ambient.domain.tables.interfaces.IXTodo> E into(E into);

    default IXTodo fromJson(io.vertx.core.json.JsonObject json) {
        setKey(json.getString("KEY"));
        setSerial(json.getString("SERIAL"));
        setName(json.getString("NAME"));
        setCode(json.getString("CODE"));
        setIcon(json.getString("ICON"));
        setStatus(json.getString("STATUS"));
        setType(json.getString("TYPE"));
        // Omitting unrecognized type java.time.LocalDateTime for column EXPIRED_AT!
        setModelId(json.getString("MODEL_ID"));
        setModelKey(json.getString("MODEL_KEY"));
        setToGroup(json.getString("TO_GROUP"));
        setToUser(json.getString("TO_USER"));
        setAssignedBy(json.getString("ASSIGNED_BY"));
        setAcceptedBy(json.getString("ACCEPTED_BY"));
        setFinishedBy(json.getString("FINISHED_BY"));
        setTraceId(json.getString("TRACE_ID"));
        setActive(json.getBoolean("ACTIVE"));
        setSigma(json.getString("SIGMA"));
        setMetadata(json.getString("METADATA"));
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
        json.put("SERIAL",getSerial());
        json.put("NAME",getName());
        json.put("CODE",getCode());
        json.put("ICON",getIcon());
        json.put("STATUS",getStatus());
        json.put("TYPE",getType());
        // Omitting unrecognized type java.time.LocalDateTime for column EXPIRED_AT!
        json.put("MODEL_ID",getModelId());
        json.put("MODEL_KEY",getModelKey());
        json.put("TO_GROUP",getToGroup());
        json.put("TO_USER",getToUser());
        json.put("ASSIGNED_BY",getAssignedBy());
        json.put("ACCEPTED_BY",getAcceptedBy());
        json.put("FINISHED_BY",getFinishedBy());
        json.put("TRACE_ID",getTraceId());
        json.put("ACTIVE",getActive());
        json.put("SIGMA",getSigma());
        json.put("METADATA",getMetadata());
        json.put("LANGUAGE",getLanguage());
        // Omitting unrecognized type java.time.LocalDateTime for column CREATED_AT!
        json.put("CREATED_BY",getCreatedBy());
        // Omitting unrecognized type java.time.LocalDateTime for column UPDATED_AT!
        json.put("UPDATED_BY",getUpdatedBy());
        return json;
    }

}
