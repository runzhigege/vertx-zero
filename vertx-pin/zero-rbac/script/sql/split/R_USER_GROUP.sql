-- liquibase formatted sql

-- changeset Lang:ox-user-group-1
-- 关联表：R_USER_GROUP
DROP TABLE IF EXISTS R_USER_GROUP;
CREATE TABLE IF NOT EXISTS R_USER_GROUP
(
    `GROUP_ID` VARCHAR(36) COMMENT "「groupId」- 关联组ID",
    `ROLE_ID`  VARCHAR(36) COMMENT "「roleId」- 关联角色ID",
    PRIMARY KEY (`GROUP_ID`, `ROLE_ID`)
);
