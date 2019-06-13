-- liquibase formatted sql

-- changeset Lang:ox-module-1
-- 应用程序中的模块表：X_MODULE
DROP TABLE IF EXISTS X_MODULE;
CREATE TABLE IF NOT EXISTS X_MODULE
(
    `KEY`       VARCHAR(36) COMMENT '「key」- 模块唯一主键',
    `NAME`      VARCHAR(255) COMMENT '「name」- 模块名称',
    `CODE`      VARCHAR(36) COMMENT '「code」- 应用程序编码',
    `CAT`       VARCHAR(20) COMMENT '「cat」- 模块类型',
    `URL_ENTRY` VARCHAR(255) COMMENT '「urlEntry」— 模块入口地址',
    `APP_ID`    VARCHAR(255) COMMENT '「appId」- 关联的应用程序ID', -- 一对多，一个App下会包含多个Module
    `MODEL_ID`  VARCHAR(36) COMMENT '「modelId」- 当前模块关联的主模型ID',
    `ACTIVE`    BIT COMMENT '「active」- 是否启用',
    `METADATA`  TEXT COMMENT '「metadata」- 附加配置数据',
    `LANGUAGE`  VARCHAR(10) COMMENT '「language」- 使用的语言',
    PRIMARY KEY (`KEY`)
);

-- changeset Lang:ox-module-2
ALTER TABLE X_MODULE
    ADD UNIQUE (`URL_ENTRY`); -- 页面入口地址