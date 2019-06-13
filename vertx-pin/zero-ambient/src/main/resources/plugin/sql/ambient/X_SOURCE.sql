-- liquibase formatted sql

-- changeset Lang:ox-source-1
-- 数据源专用表：X_SOURCE
DROP TABLE IF EXISTS X_SOURCE;
CREATE TABLE IF NOT EXISTS X_SOURCE
(
    `KEY`         VARCHAR(36) COMMENT '「key」- 数据源主键',
    `IP_V4`       VARCHAR(15) COMMENT '「ipV4」- IP v4地址',
    `IP_V6`       VARCHAR(40) COMMENT '「ipV6」- IP v6地址',
    `HOSTNAME`    VARCHAR(255) COMMENT '「hostname」- 主机地址',
    `PORT`        INTEGER COMMENT '「port」- 端口号',
    `CATEGORY`    VARCHAR(32) COMMENT '「category」- 数据库类型',
    `JDBC_URL`    VARCHAR(1024) COMMENT '「jdbcUrl」- JDBC连接字符串',
    `JDBC_CONFIG` TEXT COMMENT '「jdbcConfig」- 连接字符串中的配置key=value',
    `INSTANCE`    VARCHAR(255) COMMENT '「instance」- 实例名称',
    `USERNAME`    VARCHAR(255) COMMENT '「username」- 账号',
    `PASSWORD`    VARCHAR(255) COMMENT '「password」- 密码',

    -- 特殊属性
    `ACTIVE`      BIT COMMENT '「active」- 是否启用',
    `METADATA`    TEXT COMMENT '「metadata」- 通过MetadataConnection分析读取的数据',
    `LANGUAGE`    VARCHAR(10) COMMENT '「language」- 使用的语言',
    `APP_ID`      VARCHAR(255) COMMENT '「appId」- 关联的应用程序ID',
    PRIMARY KEY (`KEY`)
);

-- changeset Lang:ox-source-2
ALTER TABLE X_SOURCE
    ADD UNIQUE (`APP_ID`); -- 目前应用程序和数据源一对一，暂定