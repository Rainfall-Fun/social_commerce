package com.cqjtu.sc.gateway.dao.entity.admin;

import javax.persistence.*;
@Entity
@Table(name = "admin_authority")
public class AdminPermission {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int authorityId;
    private int groupId;
    private String authorityName;
    private String authorityAddress;
    private String des;

    public int getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(int authorityId) {
        this.authorityId = authorityId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    public String getAuthorityAddress() {
        return authorityAddress;
    }

    public void setAuthorityAddress(String authorityAddress) {
        this.authorityAddress = authorityAddress;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
