package com.cqjtu.membermanageservice.db;

import com.sun.org.apache.xpath.internal.operations.Bool;

public class AllUserInfo {
    public Integer userId;
    public String username;
    public String phoneNumber;
    public Boolean userSexy;
    public Integer invitor;
    public String bankNumber;
    public Boolean isValid;
    public Integer grade;
    public Integer score;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getUserSexy() {
        return userSexy;
    }

    public void setUserSexy(Boolean userSexy) {
        this.userSexy = userSexy;
    }

    public Integer getInvitor() {
        return invitor;
    }

    public void setInvitor(Integer invitor) {
        this.invitor = invitor;
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
