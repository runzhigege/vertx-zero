package io.vertx.tp.rbac.atom;

import java.io.Serializable;

/*
 * Security configuration data
 *
 */
public class ScConfig implements Serializable {
    /*
     * Unique condition for Security Entity
     * 1) User
     * 2) Role
     * 3) Group
     * 4) Permission
     * 5) Action
     * 6) Resource
     */
    private ScCondition condition;
    /*
     * Authorization Code expired time: ( s )
     */
    private Integer codeExpired;
    /*
     * Authorization Code length ( random string )
     */
    private Integer codeLength;
    /*
     * Authorization Code temp pool
     */
    private String codePool;
    /*
     * Token expired time: ( min )
     */
    private Integer tokenLength;
    /*
     * User Group Feature enabled
     * Token From:
     * {
     *      "user": "xxx",
     *      "role": ["x","y"]
     * }
     * To:
     * {
     *      "user": "xxx",
     *      "role": ["x","y"]
     *      "group": ["x","y"]
     * }
     */
    private Boolean groupSupport = Boolean.FALSE;
    /*
     * User Group Permission enabled
     * if this field = true, zero system will capture roles by group when logging instead of user only.
     * 1) groupPermission = false, Group / Role is forbidden
     *      - Token information will include:
     *      1.1. Logged user id ( `user` )
     *      1.2. Related group ids ( `group` ) JsonArray
     *      1.3. Related role ids ( `role` ) JsonArray
     * 2) groupPermission = true, Group / Role is enabled
     *      - Token information will include:
     *      1.1. Logged user id ( `user` )
     *      1.2. Related group ids ( `group` ) JsonArray
     *      1.3. Related role ids ( `role` ) JsonArray ( Each user, group and merged )
     */
    private Boolean groupPermission = Boolean.FALSE;

    public Boolean getGroupPermission() {
        return this.groupPermission;
    }

    public void setGroupPermission(final Boolean groupPermission) {
        this.groupPermission = groupPermission;
    }

    public Boolean getGroupSupport() {
        return this.groupSupport;
    }

    public void setGroupSupport(final Boolean groupSupport) {
        this.groupSupport = groupSupport;
    }

    public ScCondition getCondition() {
        return this.condition;
    }

    public void setCondition(final ScCondition condition) {
        this.condition = condition;
    }

    public Integer getCodeExpired() {
        return this.codeExpired;
    }

    public void setCodeExpired(final Integer codeExpired) {
        this.codeExpired = codeExpired;
    }

    public Integer getCodeLength() {
        return this.codeLength;
    }

    public void setCodeLength(final Integer codeLength) {
        this.codeLength = codeLength;
    }

    public String getCodePool() {
        return this.codePool;
    }

    public void setCodePool(final String codePool) {
        this.codePool = codePool;
    }

    public Integer getTokenLength() {
        return this.tokenLength;
    }

    public void setTokenLength(final Integer tokenLength) {
        this.tokenLength = tokenLength;
    }

    @Override
    public String toString() {
        return "ScConfig{" +
                "condition=" + this.condition +
                ", codeExpired=" + this.codeExpired +
                ", codeLength=" + this.codeLength +
                ", codePool='" + this.codePool + '\'' +
                ", tokenLength=" + this.tokenLength +
                ", groupSupport=" + this.groupSupport +
                ", groupPermission=" + this.groupPermission +
                '}';
    }
}
