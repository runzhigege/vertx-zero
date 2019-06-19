-- liquibase formatted sql

-- changeset Lang:ox-ajax-1
-- 对应前端配置：ajax 节点的内容
DROP TABLE IF EXISTS UI_AJAX;
CREATE TABLE IF NOT EXISTS UI_AJAX
(
    `KEY`          VARCHAR(36) COMMENT '「key」- 主键',
    `NAME`         VARCHAR(64) COMMENT '「name」- Ajax的名称，生成ajaxKey专用',
    `URI`          VARCHAR(255) COMMENT '「uri」- 后端接口的URI地址',
    `QUERY`        BIT COMMENT '「query」- 是否query类型的接口',
    `LAZY`         BIT COMMENT '「lazy」- 是否lazy模式',
    `METHOD`       VARCHAR(20) COMMENT '「method」- 当前Ajax的HTTP方法',
    -- 参数表
    `PROJECTION`   TEXT COMMENT '「projection」- query = true 生效',
    `PAGER`        TEXT COMMENT '「pager」- query = true 生效',
    `SORTER`       TEXT COMMENT '「sorter」- query = true 生效',
    `CRITERIA`     TEXT COMMENT '「criteria」- query = true则位于 query之下特殊节点，query = false则直接存储 query值',

    -- 关联类型
    `RELATED_TYPE` VARCHAR(32) COMMENT '「relatedType」- 关联类型：Page, Form',
    `RELATED_ID`   VARCHAR(255) COMMENT '「relatedId」- 关联的ID，如果是Page则是PAGE_ID，如果是Form则是FORM_ID',

    -- 特殊字段
    `ACTIVE`       BIT         DEFAULT NULL COMMENT '「active」- 是否启用',
    `SIGMA`        VARCHAR(32) DEFAULT NULL COMMENT '「sigma」- 统一标识',
    `METADATA`     TEXT COMMENT '「metadata」- 附加配置',
    `LANGUAGE`     VARCHAR(8)  DEFAULT NULL COMMENT '「language」- 使用的语言',

    -- Auditor字段
    `CREATED_AT`   DATETIME COMMENT '「createdAt」- 创建时间',
    `CREATED_BY`   VARCHAR(36) COMMENT '「createdBy」- 创建人',
    `UPDATED_AT`   DATETIME COMMENT '「updatedAt」- 更新时间',
    `UPDATED_BY`   VARCHAR(36) COMMENT '「updatedBy」- 更新人',
    -- 当前应用需要使用的所有的Ajax
    PRIMARY KEY (`KEY`)
    -- `CRITERIA`   TEXT COMMENT '「criteria」- query = true 生效',
    -- CRITERIA 依靠 UI_PARAM 中的定义，所以不在这里涉及表结构来处理
);

-- changeset Lang:ox-ajax-2
ALTER TABLE UI_AJAX
    ADD UNIQUE (`NAME`, `RELATED_ID`); -- 按某一个页面或者表单中使用的Ajax不可重复，name不重复，URI地址可以重复