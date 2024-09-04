package com.iworkcloud.mapper;

import com.iworkcloud.pojo.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
* Title: UserDao
* Description:
* 用户数据接口 
* Version:1.0.0
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    User findByUsernameAndPassword(User user);

    String findSaltByUserAccount(String useraccount);
}
