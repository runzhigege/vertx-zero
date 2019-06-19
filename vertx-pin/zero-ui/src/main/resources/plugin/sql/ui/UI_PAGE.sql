-- liquibase formatted sql

-- changeset Lang:ox-page-1
-- 应用程序中的页面表：UI_PAGE
DROP TABLE IF EXISTS UI_PAGE;
CREATE TABLE IF NOT EXISTS UI_PAGE
(
    `KEY`              VARCHAR(36) COMMENT '「key」- 页面唯一主键',
    `URL`              VARCHAR(255) COMMENT '「url」- 模块入口地址，前端统一URL，后端统一URI（术语）',
    -- URL，/APP/MODULE/PAGE?PARAMS_STRING
    `APP`              VARCHAR(255) COMMENT '「app」- 入口APP名称，APP中的path',
    `MODULE`           VARCHAR(255) COMMENT '「module」- 模块相关信息',
    `PAGE`             VARCHAR(255) COMMENT '「page」- 页面路径信息',
    `SECURE`           BIT COMMENT '「secure」- 是否执行安全检查（安全检查才会被权限系统捕捉）',
    `PARAM_MAP`        TEXT COMMENT '「paramMap」- URL地址中的配置key=value',

    -- 界面配置
    `STATE`            TEXT COMMENT '「state」- $env环境变量专用，设置默认的 initState',
    `CONTAINER_NAME`   VARCHAR(64) COMMENT '「containerName」如果包含了容器组件，设置容器组件名称',
    `CONTAINER_CONFIG` TEXT COMMENT '「containerConfig」如果包含了容器组件，设置容器组件配置信息',
    `GRID`             TEXT COMMENT '「grid」- 布局专用数据，设置Ant Design的行列结构',
    `LAYOUT_ID`        VARCHAR(36) COMMENT '「layoutId」- 使用的模板ID，最终生成 layout 顶层节点数据',

    -- 特殊字段
    `ACTIVE`           BIT         DEFAULT NULL COMMENT '「active」- 是否启用',
    `SIGMA`            VARCHAR(32) DEFAULT NULL COMMENT '「sigma」- 统一标识',
    `METADATA`         TEXT COMMENT '「metadata」- 附加配置',
    `LANGUAGE`         VARCHAR(8)  DEFAULT NULL COMMENT '「language」- 使用的语言',

    -- Auditor字段
    `CREATED_AT`       DATETIME COMMENT '「createdAt」- 创建时间',
    `CREATED_BY`       VARCHAR(36) COMMENT '「createdBy」- 创建人',
    `UPDATED_AT`       DATETIME COMMENT '「updatedAt」- 更新时间',
    `UPDATED_BY`       VARCHAR(36) COMMENT '「updatedBy」- 更新人',
    PRIMARY KEY (`KEY`)
);

-- changeset Lang:ox-page-2
ALTER TABLE UI_PAGE
    ADD UNIQUE (`URL`, `SIGMA`); -- 页面唯一地址，同一个应用内唯一