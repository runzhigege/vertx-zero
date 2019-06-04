-- liquibase formatted sql

-- changeset Lang:ox-resource-matrix-1
-- 用户/角色资源限定表
DROP TABLE IF EXISTS R_RESOURCE_MATRIX;
CREATE TABLE IF NOT EXISTS R_RESOURCE_MATRIX
(
    `KEY`         VARCHAR(36) COMMENT "「key」- 限定记录ID",
    `ROLE_ID`     VARCHAR(36) COMMENT "「roleId」- 限定角色ID",
    `USER_ID`     VARCHAR(36) COMMENT "「userId」- 限定用户ID",
    `RESOURCE_ID` VARCHAR(36) COMMENT "「resourceId」- 关联资源ID",
    `SIGMA`       VARCHAR(128) COMMENT "「sigma」- 所属APP的APPKEY",
    PRIMARY KEY (`KEY`)
);

-- changeset Lang:ox-resource-matrix-2
-- Unique
ALTER TABLE R_RESOURCE_MATRIX
    ADD UNIQUE (`SIGMA`, `RESOURCE_ID`, `USER_ID`);
ALTER TABLE R_RESOURCE_MATRIX
    ADD UNIQUE (`SIGMA`, `RESOURCE_ID`, `ROLE_ID`);
