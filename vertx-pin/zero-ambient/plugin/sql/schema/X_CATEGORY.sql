-- liquibase formatted sql

-- changeset Lang:ox-category-1
-- 树形类型数据表
DROP TABLE IF EXISTS X_CATEGORY;
CREATE TABLE IF NOT EXISTS X_CATEGORY
(
    `KEY`        VARCHAR(36) COMMENT '「key」- 类型主键',
    `NAME`       VARCHAR(255) COMMENT '「name」- 类型名称',
    `CODE`       VARCHAR(255) COMMENT '「code」- 类型编号',
    `ICON`       VARCHAR(255) COMMENT '「icon」- 类型图标',
    `TYPE`       VARCHAR(255) COMMENT '「type」- 类型的分类',
    `SORT`       INTEGER COMMENT '「sort」- 排序信息',
    `LEAF`       BIT COMMENT '「leaf」- 是否叶节点',
    `PARENT_ID`  VARCHAR(36) COMMENT '「parentId」- 父类ID',
    `IDENTIFIER` VARCHAR(255) COMMENT '「identifier」- 当前类型描述的Model的标识',
    `COMMENT`    TEXT COMMENT '「comment」- 备注信息',
    `ACTIVE`     BIT COMMENT '「active」- 是否启用',
    `METADATA`   TEXT COMMENT '「metadata」-扩展配置信息',
    `LANGUAGE`   VARCHAR(10) COMMENT '「language」- 使用的语言',
    `APP_ID`     VARCHAR(255) COMMENT '「appId」- 关联的应用程序ID',
    PRIMARY KEY (`KEY`)
);

-- changeset Lang:ox-category-2
ALTER TABLE X_CATEGORY
    ADD UNIQUE (`APP_ID`, `TYPE`, `CODE`); -- 每一个应用内的 app - type - code 维持唯一