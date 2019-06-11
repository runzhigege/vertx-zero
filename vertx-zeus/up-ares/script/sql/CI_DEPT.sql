-- liquibase formatted sql

-- changeset Lang:ox-role-1
-- 角色专用表：CI_DEPT
DROP TABLE IF EXISTS CI_DEPT;
CREATE TABLE IF NOT EXISTS CI_DEPT
(
    `KEY`            VARCHAR(36) COMMENT '「key」- 部门ID',
    `NAME`           VARCHAR(255) COMMENT '「name」- 部门名称',
    `CODE`           VARCHAR(255) COMMENT '「code」- 部门编码',
    `COMMENT`        TEXT COMMENT '「comment」- 部门备注',
    `MANAGER`        VARCHAR(255) COMMENT '「manager」- 部门经理',
    `CONTACT_MOBILE` VARCHAR(11) COMMENT '「contactMobile」- 部门联系人手机',
    `CONTACT_NAME`   VARCHAR(255) COMMENT '「contactName」- 部门联系人姓名',
    -- 特殊字段
    `APP_ID`         VARCHAR(36) COMMENT '「appId」- 部门关联的APP ID',
    `LANGUAGE`       VARCHAR(10) COMMENT '「language」- 使用的语言',
    `ACTIVE`         BIT COMMENT '「active」- 是否启用',
    `METADATA`       TEXT COMMENT '「metadata」- 附加配置数据',
    -- Auditor字段
    `CREATED_AT`     DATETIME COMMENT '「createdAt」- 创建时间',
    `CREATED_BY`     VARCHAR(36) COMMENT '「createdBy」- 创建人',
    `UPDATED_AT`     DATETIME COMMENT '「updatedAt」- 更新时间',
    `UPDATED_BY`     VARCHAR(36) COMMENT '「updatedBy」- 更新人',
    PRIMARY KEY (`KEY`)
);
-- changeset Lang:ox-role-2
-- Unique Key：独立唯一键定义
ALTER TABLE CI_DEPT
    ADD UNIQUE (`CODE`, `APP_ID`);
ALTER TABLE CI_DEPT
    ADD UNIQUE (`NAME`, `APP_ID`);