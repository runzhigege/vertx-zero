-- liquibase formatted sql

-- changeset Lang:ox-event-1
-- 核心操作类 UI_EVENT（前端Event模型）
DROP TABLE IF EXISTS UI_EVENT;
CREATE TABLE IF NOT EXISTS UI_EVENT
(
    `KEY`            VARCHAR(36) COMMENT '「key」-- 主键',
    `ACTION_ID`      VARCHAR(36) COMMENT '「actionId」-- 事件关联的ActionId（安全专用）',
    `TARGET`         VARCHAR(36) COMMENT '「target」-- 必须，事件对应的目标ID',

    -- 反向关联
    `RELATED_TYPE`   VARCHAR(64) COMMENT '「relatedType」-- 事件类型：FORM - 表单事件，COMPONENT - 组件事件',
    `RELATED_ID`     VARCHAR(36) COMMENT '「relatedId」-- 关联ID',

    -- 事件相关配置
    `AJAX`           VARCHAR(64) COMMENT '「ajax」- 对应 ajax 节点',
    `MODE`           VARCHAR(64) COMMENT '「mode」- 对应 mode 节点，$env中的合并、删除',
    `MODAL`          TEXT COMMENT '「modal」- 窗口配置',
    `QUERY`          TEXT COMMENT '「query」- 查询参数专用配置',
    `CONNECTOR`      VARCHAR(5) COMMENT '「connector」- 查询专用：AND或OR',
    `FILE`           TEXT COMMENT '「file」- 上传专用',
    `RULES`          TEXT COMMENT '「rules」- 事件专用的配置，规则',

    -- 事件核心
    `DATA_MAGIC`     TEXT COMMENT '「dataMagic」- 数据加载：config -> magic',
    `DATA_ARGUMENTS` TEXT COMMENT '「dataArguments」- 函数配置：config -> arguments',

    -- 特殊字段
    `ACTIVE`         BIT         DEFAULT NULL COMMENT '「active」- 是否启用',
    `SIGMA`          VARCHAR(32) DEFAULT NULL COMMENT '「sigma」- 统一标识',
    `METADATA`       TEXT COMMENT '「metadata」- 附加配置',
    `LANGUAGE`       VARCHAR(8)  DEFAULT NULL COMMENT '「language」- 使用的语言',

    -- Auditor字段
    `CREATED_AT`     DATETIME COMMENT '「createdAt」- 创建时间',
    `CREATED_BY`     VARCHAR(36) COMMENT '「createdBy」- 创建人',
    `UPDATED_AT`     DATETIME COMMENT '「updatedAt」- 更新时间',
    `UPDATED_BY`     VARCHAR(36) COMMENT '「updatedBy」- 更新人',
    PRIMARY KEY (`KEY`)
);