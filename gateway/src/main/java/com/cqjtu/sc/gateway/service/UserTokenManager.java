package com.cqjtu.sc.gateway.service;


import com.cqjtu.sc.gateway.util.JwtHelper;

/**
 * 维护用户token
 */
public class UserTokenManager {
	public static String generateToken(Integer id) {
        JwtHelper jwtHelper = new JwtHelper();
        return jwtHelper.createToken(id);
    }
    public static Integer getUserId(String token) {
    	JwtHelper jwtHelper = new JwtHelper();
    	Integer userId = jwtHelper.verifyTokenAndGetUserInfoId(token);
    	if(userId == null || userId == 0){
    		return null;
    	}
        return userId;
    }
}
