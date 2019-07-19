/*
 * This file is generated by jOOQ.
*/
package cn.vertxup.ui.domain.tables.pojos;


import cn.vertxup.ui.domain.tables.interfaces.IUiField;

import java.time.LocalDateTime;

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
public class UiField implements IUiField {

    private static final long serialVersionUID = 1906995700;

    private String        key;
    private Integer       xPoint;
    private Integer       yPoint;
    private String        label;
    private String        name;
    private Integer       span;
    private String        render;
    private String        optionJsx;
    private String        optionConfig;
    private String        optionItem;
    private String        rules;
    private String        dataEvent;
    private String        formId;
    private Boolean       active;
    private String        sigma;
    private String        metadata;
    private String        language;
    private LocalDateTime createdAt;
    private String        createdBy;
    private LocalDateTime updatedAt;
    private String        updatedBy;

    public UiField() {}

    public UiField(UiField value) {
        this.key = value.key;
        this.xPoint = value.xPoint;
        this.yPoint = value.yPoint;
        this.label = value.label;
        this.name = value.name;
        this.span = value.span;
        this.render = value.render;
        this.optionJsx = value.optionJsx;
        this.optionConfig = value.optionConfig;
        this.optionItem = value.optionItem;
        this.rules = value.rules;
        this.dataEvent = value.dataEvent;
        this.formId = value.formId;
        this.active = value.active;
        this.sigma = value.sigma;
        this.metadata = value.metadata;
        this.language = value.language;
        this.createdAt = value.createdAt;
        this.createdBy = value.createdBy;
        this.updatedAt = value.updatedAt;
        this.updatedBy = value.updatedBy;
    }

    public UiField(
        String        key,
        Integer       xPoint,
        Integer       yPoint,
        String        label,
        String        name,
        Integer       span,
        String        render,
        String        optionJsx,
        String        optionConfig,
        String        optionItem,
        String        rules,
        String        dataEvent,
        String        formId,
        Boolean       active,
        String        sigma,
        String        metadata,
        String        language,
        LocalDateTime createdAt,
        String        createdBy,
        LocalDateTime updatedAt,
        String        updatedBy
    ) {
        this.key = key;
        this.xPoint = xPoint;
        this.yPoint = yPoint;
        this.label = label;
        this.name = name;
        this.span = span;
        this.render = render;
        this.optionJsx = optionJsx;
        this.optionConfig = optionConfig;
        this.optionItem = optionItem;
        this.rules = rules;
        this.dataEvent = dataEvent;
        this.formId = formId;
        this.active = active;
        this.sigma = sigma;
        this.metadata = metadata;
        this.language = language;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public UiField setKey(String key) {
        this.key = key;
        return this;
    }

    @Override
    public Integer getXPoint() {
        return this.xPoint;
    }

    @Override
    public UiField setXPoint(Integer xPoint) {
        this.xPoint = xPoint;
        return this;
    }

    @Override
    public Integer getYPoint() {
        return this.yPoint;
    }

    @Override
    public UiField setYPoint(Integer yPoint) {
        this.yPoint = yPoint;
        return this;
    }

    @Override
    public String getLabel() {
        return this.label;
    }

    @Override
    public UiField setLabel(String label) {
        this.label = label;
        return this;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public UiField setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public Integer getSpan() {
        return this.span;
    }

    @Override
    public UiField setSpan(Integer span) {
        this.span = span;
        return this;
    }

    @Override
    public String getRender() {
        return this.render;
    }

    @Override
    public UiField setRender(String render) {
        this.render = render;
        return this;
    }

    @Override
    public String getOptionJsx() {
        return this.optionJsx;
    }

    @Override
    public UiField setOptionJsx(String optionJsx) {
        this.optionJsx = optionJsx;
        return this;
    }

    @Override
    public String getOptionConfig() {
        return this.optionConfig;
    }

    @Override
    public UiField setOptionConfig(String optionConfig) {
        this.optionConfig = optionConfig;
        return this;
    }

    @Override
    public String getOptionItem() {
        return this.optionItem;
    }

    @Override
    public UiField setOptionItem(String optionItem) {
        this.optionItem = optionItem;
        return this;
    }

    @Override
    public String getRules() {
        return this.rules;
    }

    @Override
    public UiField setRules(String rules) {
        this.rules = rules;
        return this;
    }

    @Override
    public String getDataEvent() {
        return this.dataEvent;
    }

    @Override
    public UiField setDataEvent(String dataEvent) {
        this.dataEvent = dataEvent;
        return this;
    }

    @Override
    public String getFormId() {
        return this.formId;
    }

    @Override
    public UiField setFormId(String formId) {
        this.formId = formId;
        return this;
    }

    @Override
    public Boolean getActive() {
        return this.active;
    }

    @Override
    public UiField setActive(Boolean active) {
        this.active = active;
        return this;
    }

    @Override
    public String getSigma() {
        return this.sigma;
    }

    @Override
    public UiField setSigma(String sigma) {
        this.sigma = sigma;
        return this;
    }

    @Override
    public String getMetadata() {
        return this.metadata;
    }

    @Override
    public UiField setMetadata(String metadata) {
        this.metadata = metadata;
        return this;
    }

    @Override
    public String getLanguage() {
        return this.language;
    }

    @Override
    public UiField setLanguage(String language) {
        this.language = language;
        return this;
    }

    @Override
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    @Override
    public UiField setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    @Override
    public String getCreatedBy() {
        return this.createdBy;
    }

    @Override
    public UiField setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    @Override
    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    @Override
    public UiField setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    @Override
    public String getUpdatedBy() {
        return this.updatedBy;
    }

    @Override
    public UiField setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("UiField (");

        sb.append(key);
        sb.append(", ").append(xPoint);
        sb.append(", ").append(yPoint);
        sb.append(", ").append(label);
        sb.append(", ").append(name);
        sb.append(", ").append(span);
        sb.append(", ").append(render);
        sb.append(", ").append(optionJsx);
        sb.append(", ").append(optionConfig);
        sb.append(", ").append(optionItem);
        sb.append(", ").append(rules);
        sb.append(", ").append(dataEvent);
        sb.append(", ").append(formId);
        sb.append(", ").append(active);
        sb.append(", ").append(sigma);
        sb.append(", ").append(metadata);
        sb.append(", ").append(language);
        sb.append(", ").append(createdAt);
        sb.append(", ").append(createdBy);
        sb.append(", ").append(updatedAt);
        sb.append(", ").append(updatedBy);

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
    public void from(IUiField from) {
        setKey(from.getKey());
        setXPoint(from.getXPoint());
        setYPoint(from.getYPoint());
        setLabel(from.getLabel());
        setName(from.getName());
        setSpan(from.getSpan());
        setRender(from.getRender());
        setOptionJsx(from.getOptionJsx());
        setOptionConfig(from.getOptionConfig());
        setOptionItem(from.getOptionItem());
        setRules(from.getRules());
        setDataEvent(from.getDataEvent());
        setFormId(from.getFormId());
        setActive(from.getActive());
        setSigma(from.getSigma());
        setMetadata(from.getMetadata());
        setLanguage(from.getLanguage());
        setCreatedAt(from.getCreatedAt());
        setCreatedBy(from.getCreatedBy());
        setUpdatedAt(from.getUpdatedAt());
        setUpdatedBy(from.getUpdatedBy());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E extends IUiField> E into(E into) {
        into.from(this);
        return into;
    }

    public UiField(io.vertx.core.json.JsonObject json) {
        fromJson(json);
    }
}