package com.cqjtu.sc.gateway.dao.entity.admin;

import javax.persistence.*;
@Entity
@Table(name = "admin_operator")
public class AdminUserInfo {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int operatorId;
    int actorId;
    String operatorAccount;
    String operatorPassword;
    int operatorIsavailable;
    int supplierId;

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public int getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(int operatorId) {
        this.operatorId = operatorId;
    }

    public int getActorId() {
        return actorId;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
    }

    public String getOperatorAccount() {
        return operatorAccount;
    }

    public void setOperatorAccount(String operatorAccount) {
        this.operatorAccount = operatorAccount;
    }

    public String getOperatorPassword() {
        return operatorPassword;
    }

    public void setOperatorPassword(String operatorPassword) {
        this.operatorPassword = operatorPassword;
    }

    public int getOperatorIsavailable() {
        return operatorIsavailable;
    }

    public void setOperatorIsavailable(int operatorIsavailable) {
        this.operatorIsavailable = operatorIsavailable;
    }
}
