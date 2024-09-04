package com.iworkcloud.service;

import com.iworkcloud.pojo.entity.User;


/**
 * 
* Title: UserService
* Description: 
* 用户接口 
* Version:1.0.0  

 */
public interface UserService extends BaseService<User>{

    Boolean findByUsernameAndPassword(User user);

    Boolean userLogin(User user);


}
