package com.cqjtu.sc.gateway.dao.entity.admin;

import javax.persistence.*;
@Entity
@Table(name = "admin_actor")
public class AdminRole {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int actorId;
    private String actorName;
//    private String desc;
//    private Boolean enabled;
//    private Boolean deleted;

    public int getActorId() {
        return actorId;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }


//
//    public String getDesc() {
//        return desc;
//    }
//
//    public void setDesc(String desc) {
//        this.desc = desc;
//    }
//
//    public Boolean getEnabled() {
//        return enabled;
//    }
//
//    public void setEnabled(Boolean enabled) {
//        this.enabled = enabled;
//    }
//
//    public Boolean getDeleted() {
//        return deleted;
//    }
//
//    public void setDeleted(Boolean deleted) {
//        this.deleted = deleted;
//    }
}
