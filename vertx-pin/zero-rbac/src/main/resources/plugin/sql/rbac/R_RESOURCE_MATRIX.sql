-- liquibase formatted sql

-- changeset Lang:ox-resource-matrix-1
-- 用户/ 角色资源限定表，用于处理资源的行、列基本信息
DROP TABLE IF EXISTS R_RESOURCE_MATRIX;
CREATE TABLE IF NOT EXISTS R_RESOURCE_MATRIX
(
    -- 资源拥有者：按角色/用户 处理
    `KEY`         VARCHAR(36) COMMENT '「key」- 限定记录ID',
    -- 用户优先模式，角色为默认（S_RESOURCE需要传入角色计算模式，多个角色处理时支持多角色的筛选字段需要保存在内
    `OWNER`       VARCHAR(36) COMMENT '「owner」- 用户 / 角色ID',
    `OWNER_TYPE`  VARCHAR(5) COMMENT '「ownerType」- ROLE 角色，USER 用户',
    `RESOURCE_ID` VARCHAR(36) COMMENT '「resourceId」- 关联资源ID',

    /*
     * 1. 针对该资源，只能查看对应的列
     *    CARD：卡片类型，针对数据库中的属性进行过滤
     *    LIST：列表类型，针对数据库中的列进行过滤
     *    FORM：表单类型，针对数据库中的字段进行过滤
     */
    `PROJECTION`  TEXT COMMENT '「projection」- 该资源的列定义（单用户处理）',
    -- 2. 针对该资源，提供查询引擎的分支条件，和传入条件进行合并
    `QUERY`       TEXT COMMENT '「query」- 该资源的行查询（单用户处理）',
    /*
     * 3. 针对特殊资源，提供IDS的主键直接命中条件，用户处理复杂场景的直接Join（本来可以依靠查询引擎处理，但这里不设置）
     *    表单不存在这种类型，主要针对列表
     *    DATA：数据层面，在列表处理过程中，ROWS中存在的ID才会显示出来，不存在的不显示
     *    META：元数据层面，在处理过程中同样只读取ROWS中存在的ID信息
     *    （注）：对于界面呈现的ReadOnly模式，存在于UI的属性中，而不是出现在安全定义部分
     */
    `ROWS`        TEXT COMMENT '「rows」- 该资源针对保存的行进行过滤',
    -- 4. 当前是否过期，过期则直接抛出异常信息（角色直接设成NULL，仅用户资源会出现该信息）
    `EXPIRED`     DATETIME COMMENT '「expired」- 资源过期时间（动态授权）',

    -- 特殊字段
    `SIGMA`       VARCHAR(128) COMMENT '「sigma」- 用户组绑定的统一标识',
    `LANGUAGE`    VARCHAR(10) COMMENT '「language」- 使用的语言',
    `ACTIVE`      BIT COMMENT '「active」- 是否启用',
    `METADATA`    TEXT COMMENT '「metadata」- 附加配置数据',

    -- Auditor字段
    `CREATED_AT`  DATETIME COMMENT '「createdAt」- 创建时间',
    `CREATED_BY`  VARCHAR(36) COMMENT '「createdBy」- 创建人',
    `UPDATED_AT`  DATETIME COMMENT '「updatedAt」- 更新时间',
    `UPDATED_BY`  VARCHAR(36) COMMENT '「updatedBy」- 更新人',
    PRIMARY KEY (`KEY`)
);

-- changeset Lang:ox-resource-matrix-2
-- Unique
-- 用户、资源：唯一记录：高优先级
-- 角色、资源：唯一记录：低优先级
ALTER TABLE R_RESOURCE_MATRIX
    ADD UNIQUE (`OWNER`, `OWNER_TYPE`, `RESOURCE_ID`);