-- liquibase formatted sql

-- changeset Lang:ox-column-1
-- 字段表：UI_COLUMN，列表专用，CONTROL中的 type = LIST 类型
DROP TABLE IF EXISTS UI_COLUMN;
CREATE TABLE IF NOT EXISTS UI_COLUMN
(
    `KEY`        VARCHAR(36) COMMENT '「key」- 列主键',
    `TITLE`      VARCHAR(255) COMMENT '「title」- 列标题',
    `DATA_INDEX` VARCHAR(255) COMMENT '「dataIndex」- 列名',
    `RENDER`     VARCHAR(64) COMMENT '「render」- 使用的Render函数',
    `WIDTH`      INTEGER COMMENT '「width」- 当前列的宽度',
    `CLASS_NAME` VARCHAR(64) COMMENT '「className」- 当前列的特殊CSS类',
    -- 系统数据
    `CONTROL_ID` VARCHAR(36) COMMENT '「controlId」- 关联的控件ID',
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

-- changeset Lang:ox-column-2
ALTER TABLE UI_COLUMN
    ADD UNIQUE (`CONTROL_ID`, `DATA_INDEX`);