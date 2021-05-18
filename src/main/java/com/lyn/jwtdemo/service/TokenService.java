package com.lyn.jwtdemo.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.lyn.jwtdemo.entry.User;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author LengYouNuan
 * @create 2021-05-18 下午4:20
 */
@Service
public class TokenService {
    /**
     * 过期时间5分钟
     */
    private static final long EXPIRE_TIME = 5 * 60 * 1000;

    public String getToken(User user) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        String token = "";
        token = JWT.create().withAudience(user.getUserID()) //将userid放到token里
                .withExpiresAt(date)  //设置过期时间
                .sign(Algorithm.HMAC256(user.getPassWord())); //将密码作为密钥
        return token;
    }
}
