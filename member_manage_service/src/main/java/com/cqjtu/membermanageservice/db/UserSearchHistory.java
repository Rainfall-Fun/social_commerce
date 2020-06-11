package com.cqjtu.membermanageservice.db;

import org.omg.CORBA.PUBLIC_MEMBER;

public class UserSearchHistory {
    public Integer searchId;
    public Integer userId;
    public String keyWords;
    public String addTime;




    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }





    public Integer getSearchId() {
        return searchId;
    }

    public void setSearchId(Integer searchId) {
        this.searchId = searchId;
    }



    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }
}


