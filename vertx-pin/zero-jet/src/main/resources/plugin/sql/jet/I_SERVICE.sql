-- liquibase formatted sql

-- changeset Lang:ox-service-1
-- 服务定义表：I_SERVICE
DROP TABLE IF EXISTS I_SERVICE;
CREATE TABLE IF NOT EXISTS I_SERVICE
(
    `KEY`                VARCHAR(36) COMMENT '「key」- 服务ID',

    -- 名空间处理
    `NAMESPACE`          VARCHAR(255) COMMENT '「namespace」- 服务所在名空间',
    `NAME`               VARCHAR(255) COMMENT '「name」- 服务名称',
    `COMMENT`            TEXT COMMENT '「comment」- 备注信息',

    -- 特殊流程触发定义
    `IS_WORKFLOW`        BIT COMMENT '「isWorkflow」- 是否驱动工作流引擎',
    `IS_GRAPHIC`         BIT COMMENT '「isGraphic」- 是否驱动图引擎',

    -- 前置脚本和后置脚本
    `IN_SCRIPT`          TEXT COMMENT '「inScript」- 本次不使用，加载脚本引擎ScriptEngine前置脚本',
    `OUT_SCRIPT`         TEXT COMMENT '「outScript」- 本次不使用，加载脚本引擎ScriptEngine后置脚本',

    /*
     * 通道定义，主要目的是创建：ActRequest 并且传给 Service 层，通道的输入为：Envelop（数据） 和 ZApi（配置）
     * 通道主要包含五种基本类型：
     * ADAPTOR：「适配器模式」访问自身数据库专用（需要初始化DAO）
     *          Input --> Database
     *
     * CONNECTOR：「连接器模式」访问第三方数据源（初始化DAO，并且初始化第三方配置）
     *          Input --> Database
     *                --> Third Part（第三方被动）
     *
     * ACTOR：「演员模式」服务端主动通道（初始化DAO，并且初始化第三方配置）
     *         Output <-- Database
     *                <-- Third Part（第三方主动）
     *
     * DIRECTOR：「导演模式」服务端主动处理（需要初始化DAO）
     *         Output <-- Database
     *
     * DEFINE：「自定义」自定义模式，这种情况下才会启用`CHANNEL_COMPONENT`字段
     */
    `CHANNEL_TYPE`       VARCHAR(20) COMMENT '「channelType」- 通道类型：ADAPTOR / CONNECTOR / ACTOR / DIRECTOR / DEFINE',
    `CHANNEL_COMPONENT`  VARCHAR(255) COMMENT '「channelComponent」- 自定义通道专用组件',

    /*
     * 中间层专用信息
     * Channel：只可见 Envelop，生成 ActRequest，并且传入
     * Component：只可见 Record，生成 ActResponse
     */
    `CONFIG_CHANNEL`     TEXT COMMENT '「configChannel」- 通道（自定义）配置信息，Channel专用',
    `CONFIG_INTEGRATION` TEXT COMMENT '「configIntegration」- 集成配置信息，第三方专用',
    `CONFIG_DATABASE`    TEXT COMMENT '「configDatabase」- 数据库配置，当前通道访问的Database',
    `CONFIG_SERVICE`     TEXT COMMENT '「configService」- 业务组件配置，业务组件专用',

    /*
     * 服务组件定义，消费 ActRequest，并且生成 ActResponse
     * 1）只消费 ActRequest，不消费 Envelop（下层转换成 ZRecord 和 ActRequest执行，不再处理 Envelop）
     * 2）当前服务组件需要使用的 Record 类型（框架初始化，提交给内置使用），必须实现 Record 接口（ZRecord对Channel不可见）
     */
    `SERVICE_RECORD`     VARCHAR(255) COMMENT '「serviceRecord」- 服务记录定义',
    `SERVICE_COMPONENT`  VARCHAR(255) COMMENT '「serviceComponent」- 服务组件定义',

    -- 当前服务描述的模型identifier
    `IDENTIFIER`         VARCHAR(255) COMMENT '「identifier」- 当前类型描述的Model的标识',

    -- 特殊字段
    `SIGMA`              VARCHAR(32) COMMENT '「sigma」- 统一标识',
    `LANGUAGE`           VARCHAR(10) COMMENT '「language」- 使用的语言',
    `ACTIVE`             BIT COMMENT '「active」- 是否启用',
    `METADATA`           TEXT COMMENT '「metadata」- 附加配置数据',

    -- Auditor字段
    `CREATED_AT`         DATETIME COMMENT '「createdAt」- 创建时间',
    `CREATED_BY`         VARCHAR(36) COMMENT '「createdBy」- 创建人',
    `UPDATED_AT`         DATETIME COMMENT '「updatedAt」- 更新时间',
    `UPDATED_BY`         VARCHAR(36) COMMENT '「updatedBy」- 更新人',
    PRIMARY KEY (`KEY`)
);
-- changeset Lang:ox-service-2
ALTER TABLE I_SERVICE
    ADD UNIQUE (`NAME`, `NAMESPACE`);