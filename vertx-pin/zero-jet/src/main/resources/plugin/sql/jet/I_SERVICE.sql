-- liquibase formatted sql

-- changeset Lang:ox-service-1
-- 服务定义表：I_SERVICE
DROP TABLE IF EXISTS I_SERVICE;
CREATE TABLE IF NOT EXISTS I_SERVICE
(
    `KEY`               VARCHAR(36) COMMENT '「key」- 服务ID',

    -- 名空间处理
    `NAMESPACE`         VARCHAR(255) COMMENT '「namespace」- 服务所在名空间',
    `NAME`              VARCHAR(255) COMMENT '「name」- 服务名称',
    `COMMENT`           TEXT COMMENT '「comment」- 备注信息',

    -- 服务类型处理 / 是否挂载
    `TYPE`              VARCHAR(20) COMMENT '「type」- 服务类型：ADAPTOR / CONNECTOR / ACTOR / DIRECTOR',
    `IS_WORKFLOW`       BIT COMMENT '「isWorkflow」- 是否驱动工作流引擎',
    `IS_GRAPHIC`        BIT COMMENT '「isGraphic」- 是否驱动图引擎',
    `IN_SCRIPT`         TEXT COMMENT '「inScript」- 本次不使用，加载脚本引擎ScriptEngine前置脚本',
    `OUT_SCRIPT`        TEXT COMMENT '「outScript」- 本次不使用，加载脚本引擎ScriptEngine后置脚本',

    -- 服务组件定义
    `SERVICE_CHANNEL`   VARCHAR(255) COMMENT '「serviceChannel」- 服务通道定义',
    `SERVICE_COMPONENT` VARCHAR(255) COMMENT '「serviceComponent」- 服务组件定义',
    `SERVICE_CONFIG`    TEXT COMMENT '「serviceConfig」- 服务配置描述',
    `SERVICE_SPEC`      TEXT COMMENT '「serviceSpec」- 服务规范描述',
    `SERVICE_RESPONSER` VARCHAR(255) COMMENT '「serviceResponser」- 服务响应器',

    -- 当前服务描述的模型identifier
    `IDENTIFIER`        VARCHAR(255) COMMENT '「identifier」- 当前类型描述的Model的标识',

    -- 特殊字段
    `SIGMA`             VARCHAR(32) COMMENT '「sigma」- 统一标识',
    `LANGUAGE`          VARCHAR(10) COMMENT '「language」- 使用的语言',
    `ACTIVE`            BIT COMMENT '「active」- 是否启用',
    `METADATA`          TEXT COMMENT '「metadata」- 附加配置数据',

    -- Auditor字段
    `CREATED_AT`        DATETIME COMMENT '「createdAt」- 创建时间',
    `CREATED_BY`        VARCHAR(36) COMMENT '「createdBy」- 创建人',
    `UPDATED_AT`        DATETIME COMMENT '「updatedAt」- 更新时间',
    `UPDATED_BY`        VARCHAR(36) COMMENT '「updatedBy」- 更新人',
    PRIMARY KEY (`KEY`)
);
-- changeset Lang:ox-service-2
ALTER TABLE I_SERVICE
    ADD UNIQUE (`NAME`, `NAMESPACE`);