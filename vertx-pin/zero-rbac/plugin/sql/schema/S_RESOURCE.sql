-- liquibase formatted sql

-- changeset Lang:ox-resource-1
-- 资源表：S_RESOURCE
DROP TABLE IF EXISTS S_RESOURCE;
CREATE TABLE IF NOT EXISTS S_RESOURCE
(
    `KEY`        VARCHAR(36) COMMENT '「key」- 资源对应的ID',
    `CODE`       VARCHAR(255) COMMENT '「code」- 资源编号',
    `NAME`       VARCHAR(255) COMMENT '「name」- 资源名称',
    `COMMENT`    TEXT COMMENT '「comment」- 备注信息',

    -- 资源属性
    `PROJECTION` TEXT COMMENT '「projection」- 该资源的列定义',
    `QUERY`      TEXT COMMENT '「query」- 该资源的行查询',
    `LEVEL`      INTEGER COMMENT '「level」- 资源需求级别',
    `EXPIRED`    DATETIME COMMENT '「expired」- 资源过期时间（动态授权）',

    -- 计算Profile专用（资源所需的Profile详细信息）
    `MODE_ROLE`  VARCHAR(32) COMMENT '「modeRole」- 该资源查找角色的模式',
    `MODE_GROUP` VARCHAR(32) COMMENT '「modeGroup」- 该资源查找组的模式',
    `MODE_TREE`  VARCHAR(32) COMMENT '「modeTree」- 该资源处理树（用户组）的模式',

    -- 资源标识
    `SIGMA`      VARCHAR(32) COMMENT '「sigma」- 统一标识',

    -- 模块相关 Join
    `MODEL_ID`   VARCHAR(255) COMMENT '「modelId」- 资源对应的模型identifier',
    `MODEL_KEY`  VARCHAR(36) COMMENT '「modelKey」- 资源对应的单条记录ID',

    -- 特殊字段
    `CATEGORY`   VARCHAR(36) COMMENT '「category」- 资源分类',
    `LANGUAGE`   VARCHAR(10) COMMENT '「language」- 使用的语言',
    `ACTIVE`     BIT COMMENT '「active」- 是否启用',
    `METADATA`   TEXT COMMENT '「metadata」- 附加配置数据',

    -- Auditor字段
    `CREATED_AT` DATETIME COMMENT '「createdAt」- 创建时间',
    `CREATED_BY` VARCHAR(36) COMMENT '「createdBy」- 创建人',
    `UPDATED_AT` DATETIME COMMENT '「updatedAt」- 更新时间',
    `UPDATED_BY` VARCHAR(36) COMMENT '「updatedBy」- 更新人',
    PRIMARY KEY (`KEY`)
);