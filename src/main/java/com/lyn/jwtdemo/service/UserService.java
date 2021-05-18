package com.lyn.jwtdemo.service;

import com.lyn.jwtdemo.entry.User;
import org.springframework.stereotype.Service;

/**
 * @author LengYouNuan
 * @create 2021-05-18 下午4:16
 */
@Service
public class UserService {
    public User getUser(String userid, String password){
        if ("admin".equals(userid) && "admin".equals(password)){
            User user=new User();
            user.setUserID("admin");
            user.setUserName("admin");
            user.setPassWord("admin");
            return user;
        }
        else{
            return null;
        }
    }

    public User getUser(String userid){
        if ("admin".equals(userid)){
            User user=new User();
            user.setUserID("admin");
            user.setUserName("admin");
            user.setPassWord("admin");
            return user;
        }
        else{
            return null;
        }
    }
}
