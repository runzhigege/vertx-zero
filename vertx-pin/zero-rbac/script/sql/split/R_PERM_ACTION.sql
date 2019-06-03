-- liquibase formatted sql

-- changeset Lang:ox-perm-action-1
-- 关联表：R_PERM_ACTION
DROP TABLE IF EXISTS R_PERM_ACTION;
CREATE TABLE IF NOT EXISTS R_PERM_ACTION
(
    `PERM_ID`   VARCHAR(36) COMMENT "「permId」- 关联权限ID",
    `ACTION_ID` VARCHAR(36) COMMENT "「actionId」- 操作ID",
    PRIMARY KEY (`PERM_ID`, `ACTION_ID`)
);
