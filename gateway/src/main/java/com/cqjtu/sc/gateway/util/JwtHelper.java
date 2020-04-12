package com.cqjtu.sc.gateway.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.*;

public class JwtHelper {
    //the secret key
    static final String SECRET = "S-C-Token";
    //who generated the signature
    static final String ISSUSER = "SCMALL";
    //the theme of signature
    static final String SUBJECT = "this is scmall token";
    //the audience of signature
    static final String AUDIENCE = "wxgzh";
    public String createToken(Integer userInfoId){
        try {
            Algorithm algorithm=Algorithm.HMAC256(SECRET);
            HashMap<String, Object> map = new HashMap<>();
            Date nowDate=new Date();
            //expiration time: 2 hours
            Date expireDate=getAfterDate(nowDate,0,0,0,2,0,0);
            map.put("alg","HS256");
            map.put("typ","JWT");
            String token= JWT.create()
                    //set cephalic information:header
                    .withHeader(map)
                    //set payload
                    .withClaim("userInfoId",userInfoId)
                    .withIssuer(ISSUSER)
                    .withSubject(SUBJECT)
                    .withAudience(AUDIENCE)
                    //the time of generated signature
                    .withIssuedAt(nowDate)
                    //expiration time
                    .withExpiresAt(expireDate)
                    .sign(algorithm);
            return token;
        }catch (JWTCreationException exception){
            exception.printStackTrace();
        }
        return null;
    }
    public Date getAfterDate(Date date,int year,int month,int day,int hour,int minute,int second){
        if (date==null){
            date=new Date();
        }
        Calendar cal=new GregorianCalendar();
        cal.setTime(date);
        if (year!=0) {
            cal.add(Calendar.YEAR,year);
        }
        if (month!=0) {
            cal.add(Calendar.MONTH,month);
        }
        if (day!=0) {
            cal.add(Calendar.DATE,day);
        }
        if (hour!=0) {
            cal.add(Calendar.HOUR_OF_DAY,hour);
        }
        if (minute!=0) {
            cal.add(Calendar.MINUTE,minute);
        }
        if (second!=0) {
            cal.add(Calendar.SECOND,second);
        }
        return cal.getTime();
    }

    public Integer verifyTokenAndGetUserInfoId(String token){
        try{
            Algorithm algorithm=Algorithm.HMAC256(SECRET);
            JWTVerifier verifier=JWT.require(algorithm)
                    .withIssuer(ISSUSER)
                    .build();
            DecodedJWT jwt=verifier.verify(token);
            Map<String, Claim> claims = jwt.getClaims();
            Claim userInfoId = claims.get("userInfoId");
            return userInfoId.asInt();
        }catch (JWTVerificationException exception){
            //
        }
        return null;
    }
}
