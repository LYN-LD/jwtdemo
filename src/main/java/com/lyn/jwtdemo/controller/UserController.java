package com.lyn.jwtdemo.controller;

import com.lyn.jwtdemo.annotation.LoginToken;
import com.lyn.jwtdemo.entry.User;
import com.lyn.jwtdemo.service.TokenService;
import com.lyn.jwtdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author LengYouNuan
 * @create 2021-05-18 下午4:17
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    @PostMapping("login")
    public Object login(String username, String password){
        HashMap hashMap=new HashMap();
        User user=userService.getUser(username, password);
        if(user==null){
            hashMap.put("message","登录失败！");
            return hashMap;
        }else {
            String token = tokenService.getToken(user);
            hashMap.put("token", token);
            hashMap.put("user", user);
            return hashMap;
        }
    }

    @LoginToken
    @PostMapping("/getMessage")
    public String getMessage(){
        return "你已通过验证";
    }
}
