-- liquibase formatted sql

-- changeset Lang:ox-resource-matrix-1
-- 用户/角色资源限定表
DROP TABLE IF EXISTS R_RESOURCE_MATRIX;
CREATE TABLE IF NOT EXISTS R_RESOURCE_MATRIX
(
    `KEY`             VARCHAR(36) COMMENT '「key」- 限定记录ID',
    `USER_ID`         VARCHAR(36) COMMENT '「userId」- 限定用户ID',
    `RESOURCE_ID`     VARCHAR(36) COMMENT '「resourceId」- 关联资源ID',

    -- 资源属性
    `PROJECTION`      TEXT COMMENT '「projection」- 该资源的列定义（单用户处理）',
    `QUERY`           TEXT COMMENT '「query」- 该资源的行查询（单用户处理）',

    -- Matrix模式
    `MODE_PROJECTION` VARCHAR(32) COMMENT '「modeProject」- 资源过滤模式',
    `MODE_QUERY`      VARCHAR(32) COMMENT '「modeQuery」- 资源查询模式',
    `SIGMA`           VARCHAR(128) COMMENT '「sigma」- 所属APP的APPKEY',
    PRIMARY KEY (`KEY`)
);

-- changeset Lang:ox-resource-matrix-2
-- Unique
ALTER TABLE R_RESOURCE_MATRIX
    ADD UNIQUE (`SIGMA`, `RESOURCE_ID`, `USER_ID`);
