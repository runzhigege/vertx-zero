-- liquibase formatted sql

-- changeset Lang:ox-control-1
-- 控件表：X_CONTROL
DROP TABLE IF EXISTS UI_CONTROL;
CREATE TABLE IF NOT EXISTS UI_CONTROL
(
    `KEY`              VARCHAR(36) COMMENT '「key」- 主键',
    -- UI配置
    -- container
    `CONTAINER_NAME`   VARCHAR(64) COMMENT '「containerName」- 当前控件使用的容器名',
    `CONTAINER_CONFIG` TEXT COMMENT '「containerConfig」- 当前控件使用的容器配置',
    -- component
    `COMPONENT_NAME`   VARCHAR(64) COMMENT '「componentName」- 当前控件使用的组件名',
    `COMPONENT_DATA`   VARCHAR(255) COMMENT '「componentData」- 当前控件绑定的ajax数据名',
    `COMPONENT_CONFIG` TEXT COMMENT '「componentConfig」- 当前控件使用的配置',
    -- 数据流处理
    `DATA_EVENT`       VARCHAR(32) COMMENT '「dataEvent] - 当前控件绑定的事件类型：单事件、并行、串行',
    `DATA_MAPPING`     TEXT COMMENT '「dataMapping」- 数据映射：config -> mapping',
    `DATA_MAGIC`       TEXT COMMENT '「dataMagic」- 数据加载：config -> magic',

    -- 系统信息
    `SIGN`             VARCHAR(64) COMMENT '「sign」- 控件使用的签名基本信息',
    `PAGE_ID`          VARCHAR(36) COMMENT '「pageId」- 当前控件所在的页面ID',

    -- 特殊字段
    `ACTIVE`           BIT         DEFAULT NULL COMMENT '「active」- 是否启用',
    `SIGMA`            VARCHAR(32) DEFAULT NULL COMMENT '「sigma」- 统一标识',
    `METADATA`         TEXT COMMENT '「metadata」- 附加配置',
    `LANGUAGE`         VARCHAR(8)  DEFAULT NULL COMMENT '「language」- 使用的语言',

    -- Auditor字段
    `CREATED_AT`       DATETIME COMMENT '「createdAt」- 创建时间',
    `CREATED_BY`       VARCHAR(36) COMMENT '「createdBy」- 创建人',
    `UPDATED_AT`       DATETIME COMMENT '「updatedAt」- 更新时间',
    `UPDATED_BY`       VARCHAR(36) COMMENT '「updatedBy」- 更新人',
    PRIMARY KEY (`KEY`)
);

-- changeset Lang:ox-control-2
ALTER TABLE UI_CONTROL
    ADD UNIQUE (`SIGN`); -- 控件签名全局唯一