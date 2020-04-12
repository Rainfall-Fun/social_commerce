package com.cqjtu.sc.gateway.dao.entity.admin;

import org.hibernate.annotations.Table;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class AdminPermissions {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int id;
    private int userInfoId;
    private int permissionId;

    public int getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(int userInfoId) {
        this.userInfoId = userInfoId;
    }

    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }
}
