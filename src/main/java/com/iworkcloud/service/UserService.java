package com.iworkcloud.service;

import com.iworkcloud.pojo.User;

import java.util.Map;


/**
 * 
* Title: UserService
* Description: 
* 用户接口 
* Version:1.0.0  

 */
public interface UserService extends BaseService<User>{

    Boolean cheakUserPassword(User user,String password);
    User findUserByAccount(String str);
    Map<String, String> encryptPasswords(String pwd);

}
