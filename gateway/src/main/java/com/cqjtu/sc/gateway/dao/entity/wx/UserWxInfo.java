package com.cqjtu.sc.gateway.dao.entity.wx;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "wx_user_wx_info")
public class UserWxInfo {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int wxInfoId;
    int userInfoId;
    String openid;
    String nickname;
    String avatarUrl;

    public int getWxInfoId() {
        return wxInfoId;
    }

    public void setWxInfoId(int wxInfoId) {
        this.wxInfoId = wxInfoId;
    }

    public int getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(int userInfoId) {
        this.userInfoId = userInfoId;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
