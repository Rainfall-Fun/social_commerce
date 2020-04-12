package com.cqjtu.sc.gateway.dao.entity.admin;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AdminRolePermissions {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int id;
    private int roleId;
    private int permissionId;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }
}
