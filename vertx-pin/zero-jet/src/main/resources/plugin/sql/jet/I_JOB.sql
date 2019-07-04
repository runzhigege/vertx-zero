-- liquibase formatted sql

-- changeset Lang:ox-job-1
-- 任务定义表：I_SERVICE
DROP TABLE IF EXISTS I_JOB;
CREATE TABLE IF NOT EXISTS I_JOB
(
    `KEY`         VARCHAR(36) COMMENT '「key」- 任务ID',

    -- 名空间处理
    `NAMESPACE`   VARCHAR(255) COMMENT '「namespace」- 任务所在名空间',
    `NAME`        VARCHAR(255) COMMENT '「name」- 任务名称',
    `CODE`        VARCHAR(255) COMMENT '「comment」- 任务编码',

    -- 存储对应的数据
    `TYPE`        VARCHAR(20) COMMENT '「type」- 任务类型',
    `COMMENT`     TEXT COMMENT '「comment」- 备注信息',
    `ADDITIONAL`  TEXT COMMENT '「additional」- 额外配置信息',

    -- JOB基本配置（包括调用基本信息）
    `RUN_AT`      DATETIME COMMENT '「runAt」- 定时任务中的JOB时间',
    `DURATION`    BIGINT COMMENT '「duration」- JOB的间隔时间',
    `PROXY`       VARCHAR(255) COMMENT '「proxy」- 代理类，带有@On/@Off',

    -- IN和OUT专用
    `ADDRESS_IN`  VARCHAR(255) COMMENT '「addressIn」- 入地址',
    `INCOME`      VARCHAR(255) COMMENT '「income」- 入组件JobIncome',
    `ADDRESS_OUT` VARCHAR(255) COMMENT '「addressOut」- 出地址',
    `OUTCOME`     VARCHAR(255) COMMENT '「outcome」- 出组件JobOutcome',

    -- TASK关联的ServiceID，后续执行Service专用，不关联则提供单独的TASK实现
    `SERVICE_ID`  VARCHAR(36) COMMENT '「serviceId」- 关联的服务ID',

    -- 特殊字段
    `SIGMA`       VARCHAR(32) COMMENT '「sigma」- 统一标识',
    `LANGUAGE`    VARCHAR(10) COMMENT '「language」- 使用的语言',
    `ACTIVE`      BIT COMMENT '「active」- 是否启用',
    `METADATA`    TEXT COMMENT '「metadata」- 附加配置数据',

    -- Auditor字段
    `CREATED_AT`  DATETIME COMMENT '「createdAt」- 创建时间',
    `CREATED_BY`  VARCHAR(36) COMMENT '「createdBy」- 创建人',
    `UPDATED_AT`  DATETIME COMMENT '「updatedAt」- 更新时间',
    `UPDATED_BY`  VARCHAR(36) COMMENT '「updatedBy」- 更新人',
    PRIMARY KEY (`KEY`)
);
-- changeset Lang:ox-job-2
ALTER TABLE I_JOB
    ADD UNIQUE (`SIGMA`, `NAMESPACE`, `CODE`);