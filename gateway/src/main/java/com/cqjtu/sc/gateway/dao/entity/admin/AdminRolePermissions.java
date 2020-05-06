package com.cqjtu.sc.gateway.dao.entity.admin;

import javax.persistence.*;

@Entity
@Table(name = "admin_actor_authority")
public class AdminRolePermissions {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int ruleAuthId;
    private int actorId;
    private int authorityId;

    public int getRuleAuthId() {
        return ruleAuthId;
    }

    public void setRuleAuthId(int ruleAuthId) {
        this.ruleAuthId = ruleAuthId;
    }

    public int getActorId() {
        return actorId;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
    }

    public int getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(int authorityId) {
        this.authorityId = authorityId;
    }
}
