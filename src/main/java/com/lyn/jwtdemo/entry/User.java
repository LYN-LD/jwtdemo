package com.lyn.jwtdemo.entry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LengYouNuan
 * @create 2021-05-18 下午4:03
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private String userID;
    private String userName;
    private String passWord;
}
