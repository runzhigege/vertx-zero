-- liquibase formatted sql

-- changeset Lang:ox-app-1
-- 应用程序表：X_APP
DROP TABLE IF EXISTS X_APP;
CREATE TABLE IF NOT EXISTS X_APP
(
    `KEY`       VARCHAR(36) COMMENT '「key」- 应用程序主键',
    `NAME`      VARCHAR(255) COMMENT '「name」- 应用程序名称',
    `CODE`      VARCHAR(36) COMMENT '「code」- 应用程序编码',
    -- 常用属性
    `TITLE`     VARCHAR(64) COMMENT '「title」- 应用程序标题',
    `LOGO`      VARCHAR(255) COMMENT '「logo」- 应用程序图标',
    `DOMAIN`    VARCHAR(255) COMMENT '「domain」- 应用程序所在域',
    `APP_PORT`  INTEGER COMMENT '「appPort」- 应用程序端口号，和SOURCE的端口号区别开',
    `URL_ENTRY` VARCHAR(255) COMMENT '「urlEntry」— 应用程序入口页面（登录页）',
    `URL_MAIN`  VARCHAR(255) COMMENT '「urlMain」- 应用程序内置主页（带安全）',

    -- 两个路由和标识
    `PATH`      VARCHAR(255) COMMENT '「path」- 应用程序路径',
    `ROUTE`     VARCHAR(255) COMMENT '「route」- 后端API的根路径，启动时需要',
    `APP_KEY`   VARCHAR(128) COMMENT '「appKey」- 应用程序专用唯一hashKey',
    `SIGMA`     VARCHAR(32) COMMENT '「sigma」- 非系统模块使用的标识',

    -- 系统属性
    `ACTIVE`    BIT COMMENT '「active」- 是否启用',
    `LANGUAGE`  VARCHAR(10) COMMENT '「language」- 使用的语言',
    `METADATA`  TEXT COMMENT '「metadata」- 附加配置数据',
    PRIMARY KEY (`KEY`)
);

-- changeset Lang:ox-app-2
-- Unique JsonKeys：独立唯一键定义
ALTER TABLE X_APP
    ADD UNIQUE (`CODE`);
ALTER TABLE X_APP
    ADD UNIQUE (`PATH`, `URL_ENTRY`); -- 应用唯一入口
ALTER TABLE X_APP
    ADD UNIQUE (`PATH`, `URL_MAIN`); -- 应用唯一主页
ALTER TABLE X_APP
    ADD UNIQUE (`NAME`); -- 应用程序名称唯一（这是系统名称）