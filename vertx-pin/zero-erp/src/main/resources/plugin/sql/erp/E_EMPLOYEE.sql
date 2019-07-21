-- liquibase formatted sql


SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- changeset Lang:h-employee-1
-- ----------------------------
-- Table structure for E_EMPLOYEE
-- ----------------------------
DROP TABLE IF EXISTS `E_EMPLOYEE`;
CREATE TABLE `E_EMPLOYEE`
(
    `KEY`           VARCHAR(36) NOT NULL COMMENT '「key」- 员工主键',
    `COMPANY_ID`    VARCHAR(36) COMMENT '「companyId」- 所属公司',
    `DEPT_ID`       VARCHAR(36) COMMENT '「deptId」- 父部门',
    `IDENTITY_ID`   VARCHAR(36) COMMENT '「identityId」- 身份关联ID',

    -- 办公信息
    `WORK_NUMBER`   VARCHAR(255) COMMENT '「workNumber」- 工号',
    `WORK_TITLE`    VARCHAR(255) COMMENT '「workTitle」- 头衔',
    `WORK_EMAIL`    VARCHAR(255) COMMENT '「workEmail」- 办公邮箱',
    `WORK_LOCATION` TEXT COMMENT '「workLocation」- 办公地点',
    `WORK_PHONE`    VARCHAR(20) COMMENT '「workPhone」- 办公电话',

    -- 特殊字段
    `TYPE`          VARCHAR(36) COMMENT '「type」- 员工分类',
    `METADATA`      TEXT COMMENT '「metadata」- 附加配置',
    `ACTIVE`        BIT         DEFAULT NULL COMMENT '「active」- 是否启用',
    `SIGMA`         VARCHAR(32) DEFAULT NULL COMMENT '「sigma」- 统一标识（公司所属应用）',
    `LANGUAGE`      VARCHAR(8)  DEFAULT NULL COMMENT '「language」- 使用的语言',

    -- Auditor字段
    `CREATED_AT`    DATETIME COMMENT '「createdAt」- 创建时间',
    `CREATED_BY`    VARCHAR(36) COMMENT '「createdBy」- 创建人',
    `UPDATED_AT`    DATETIME COMMENT '「updatedAt」- 更新时间',
    `UPDATED_BY`    VARCHAR(36) COMMENT '「updatedBy」- 更新人',
    PRIMARY KEY (`KEY`)
);
-- changeset Lang:h-employee-2
ALTER TABLE E_EMPLOYEE
    ADD UNIQUE (`WORK_NUMBER`, `COMPANY_ID`);