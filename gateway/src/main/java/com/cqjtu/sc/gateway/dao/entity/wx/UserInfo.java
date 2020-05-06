package com.cqjtu.sc.gateway.dao.entity.wx;

import javax.persistence.*;

@Entity
@Table(name = "all_user_info")
public class UserInfo {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int userInfoId;
    int regionId;
    int invitor;
    String bankNumber;
    int isValid;
    String memberNumber;

    public int getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(int userInfoId) {
        this.userInfoId = userInfoId;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public int getInvitor() {
        return invitor;
    }

    public void setInvitor(int invitor) {
        this.invitor = invitor;
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    public int getIsValid() {
        return isValid;
    }

    public void setIsValid(int isValid) {
        this.isValid = isValid;
    }

    public String getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(String memberNumber) {
        this.memberNumber = memberNumber;
    }
}
