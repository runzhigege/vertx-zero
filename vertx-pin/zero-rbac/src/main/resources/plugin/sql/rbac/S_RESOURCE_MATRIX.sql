-- liquibase formatted sql

-- changeset Lang:ox-resource-matrix-1
-- 用户/ 角色资源限定表，用于处理资源的行、列基本信息
DROP TABLE IF EXISTS R_RESOURCE_MATRIX;
CREATE TABLE IF NOT EXISTS R_RESOURCE_MATRIX
(
    -- 资源拥有者：按角色/用户/组 处理
    `KEY`         VARCHAR(36) COMMENT '「key」- 限定记录ID',
    `OWNER`       VARCHAR(36) COMMENT '「owner」- 用户 / 组 / 角色ID',
    `RESOURCE_ID` VARCHAR(36) COMMENT '「resourceId」- 关联资源ID',

    -- 资源属性信息
    `PROJECTION`  TEXT COMMENT '「projection」- 该资源的列定义（单用户处理）',
    `QUERY`       TEXT COMMENT '「query」- 该资源的行查询（单用户处理）',
    `IDS`         TEXT COMMENT '「ids」- 该资源针对用户或角色的过滤处理',
    `EXPIRED`     DATETIME COMMENT '「expired」- 资源过期时间（动态授权）',
    PRIMARY KEY (`KEY`)
);

-- changeset Lang:ox-resource-matrix-2
-- Unique
