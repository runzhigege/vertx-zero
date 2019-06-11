/*
 * This file is generated by jOOQ.
*/
package cn.vertxup.ambient.tables.pojos;


import cn.vertxup.ambient.tables.interfaces.IXMenu;

import javax.annotation.Generated;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.8"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class XMenu implements IXMenu {

    private static final long serialVersionUID = -559677084;

    private String  key;
    private String  name;
    private String  icon;
    private String  text;
    private String  uri;
    private String  type;
    private Long    order;
    private Long    level;
    private String  parentId;
    private String  appId;
    private Boolean engine;
    private Boolean active;
    private String  language;
    private String  metadata;

    public XMenu() {}

    public XMenu(XMenu value) {
        this.key = value.key;
        this.name = value.name;
        this.icon = value.icon;
        this.text = value.text;
        this.uri = value.uri;
        this.type = value.type;
        this.order = value.order;
        this.level = value.level;
        this.parentId = value.parentId;
        this.appId = value.appId;
        this.engine = value.engine;
        this.active = value.active;
        this.language = value.language;
        this.metadata = value.metadata;
    }

    public XMenu(
        String  key,
        String  name,
        String  icon,
        String  text,
        String  uri,
        String  type,
        Long    order,
        Long    level,
        String  parentId,
        String  appId,
        Boolean engine,
        Boolean active,
        String  language,
        String  metadata
    ) {
        this.key = key;
        this.name = name;
        this.icon = icon;
        this.text = text;
        this.uri = uri;
        this.type = type;
        this.order = order;
        this.level = level;
        this.parentId = parentId;
        this.appId = appId;
        this.engine = engine;
        this.active = active;
        this.language = language;
        this.metadata = metadata;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public XMenu setKey(String key) {
        this.key = key;
        return this;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public XMenu setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String getIcon() {
        return this.icon;
    }

    @Override
    public XMenu setIcon(String icon) {
        this.icon = icon;
        return this;
    }

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public XMenu setText(String text) {
        this.text = text;
        return this;
    }

    @Override
    public String getUri() {
        return this.uri;
    }

    @Override
    public XMenu setUri(String uri) {
        this.uri = uri;
        return this;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public XMenu setType(String type) {
        this.type = type;
        return this;
    }

    @Override
    public Long getOrder() {
        return this.order;
    }

    @Override
    public XMenu setOrder(Long order) {
        this.order = order;
        return this;
    }

    @Override
    public Long getLevel() {
        return this.level;
    }

    @Override
    public XMenu setLevel(Long level) {
        this.level = level;
        return this;
    }

    @Override
    public String getParentId() {
        return this.parentId;
    }

    @Override
    public XMenu setParentId(String parentId) {
        this.parentId = parentId;
        return this;
    }

    @Override
    public String getAppId() {
        return this.appId;
    }

    @Override
    public XMenu setAppId(String appId) {
        this.appId = appId;
        return this;
    }

    @Override
    public Boolean getEngine() {
        return this.engine;
    }

    @Override
    public XMenu setEngine(Boolean engine) {
        this.engine = engine;
        return this;
    }

    @Override
    public Boolean getActive() {
        return this.active;
    }

    @Override
    public XMenu setActive(Boolean active) {
        this.active = active;
        return this;
    }

    @Override
    public String getLanguage() {
        return this.language;
    }

    @Override
    public XMenu setLanguage(String language) {
        this.language = language;
        return this;
    }

    @Override
    public String getMetadata() {
        return this.metadata;
    }

    @Override
    public XMenu setMetadata(String metadata) {
        this.metadata = metadata;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("XMenu (");

        sb.append(key);
        sb.append(", ").append(name);
        sb.append(", ").append(icon);
        sb.append(", ").append(text);
        sb.append(", ").append(uri);
        sb.append(", ").append(type);
        sb.append(", ").append(order);
        sb.append(", ").append(level);
        sb.append(", ").append(parentId);
        sb.append(", ").append(appId);
        sb.append(", ").append(engine);
        sb.append(", ").append(active);
        sb.append(", ").append(language);
        sb.append(", ").append(metadata);

        sb.append(")");
        return sb.toString();
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public void from(IXMenu from) {
        setKey(from.getKey());
        setName(from.getName());
        setIcon(from.getIcon());
        setText(from.getText());
        setUri(from.getUri());
        setType(from.getType());
        setOrder(from.getOrder());
        setLevel(from.getLevel());
        setParentId(from.getParentId());
        setAppId(from.getAppId());
        setEngine(from.getEngine());
        setActive(from.getActive());
        setLanguage(from.getLanguage());
        setMetadata(from.getMetadata());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E extends IXMenu> E into(E into) {
        into.from(this);
        return into;
    }

    public XMenu(io.vertx.core.json.JsonObject json) {
        fromJson(json);
    }
}
