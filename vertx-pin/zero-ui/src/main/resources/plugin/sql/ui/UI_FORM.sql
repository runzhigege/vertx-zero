-- liquibase formatted sql

-- changeset Lang:ox-form-1
-- 表单类：UI_FORM（Form使用主键命中，所以不考虑关联关系）
DROP TABLE IF EXISTS UI_FORM;
CREATE TABLE IF NOT EXISTS UI_FORM
(
    `KEY`        VARCHAR(36) COMMENT '「key」- 主键',
    `NAME`       VARCHAR(255) COMMENT '「name」- 应用程序名称',
    `CODE`       VARCHAR(36) COMMENT '「code」- 应用程序编码',
    `WINDOW`     INTEGER COMMENT '「window」- Form对应的窗口配置',
    `HIDDEN`     TEXT COMMENT '「hidden」- 隐藏字段专用配置',
    `ROW`        TEXT COMMENT '「rowConfig/rowClass」- 行专用配置',
    `UI`         TEXT COMMENT '「ui」根据UI_FIELD中的内容生成',
    `TYPE`       VARCHAR(20) COMMENT '「type」- 表单类型，STANDARD / SUBFORM ',

    -- 特殊字段
    `ACTIVE`     BIT         DEFAULT NULL COMMENT '「active」- 是否启用',
    `SIGMA`      VARCHAR(32) DEFAULT NULL COMMENT '「sigma」- 统一标识',
    `METADATA`   TEXT COMMENT '「metadata」- 附加配置',
    `LANGUAGE`   VARCHAR(8)  DEFAULT NULL COMMENT '「language」- 使用的语言',

    -- Auditor字段
    `CREATED_AT` DATETIME COMMENT '「createdAt」- 创建时间',
    `CREATED_BY` VARCHAR(36) COMMENT '「createdBy」- 创建人',
    `UPDATED_AT` DATETIME COMMENT '「updatedAt」- 更新时间',
    `UPDATED_BY` VARCHAR(36) COMMENT '「updatedBy」- 更新人',
    PRIMARY KEY (`KEY`)
);

-- changeset Lang:ox-form-2
ALTER TABLE UI_FORM
    ADD UNIQUE (`CODE`, `SIGMA`);
