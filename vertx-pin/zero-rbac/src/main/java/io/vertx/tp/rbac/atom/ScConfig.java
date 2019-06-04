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
                '}';
    }
}
