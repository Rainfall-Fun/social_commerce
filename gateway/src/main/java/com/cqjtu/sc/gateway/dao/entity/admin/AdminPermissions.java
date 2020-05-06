package com.cqjtu.sc.gateway.dao.entity.admin;


import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "admin_operator_authority")
public class AdminPermissions {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int operatorAuthId;
    private int operatorId;
    private int authorityId;

    public int getOperatorAuthId() {
        return operatorAuthId;
    }

    public void setOperatorAuthId(int operatorAuthId) {
        this.operatorAuthId = operatorAuthId;
    }

    public int getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(int operatorId) {
        this.operatorId = operatorId;
    }

    public int getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(int authorityId) {
        this.authorityId = authorityId;
    }
}
