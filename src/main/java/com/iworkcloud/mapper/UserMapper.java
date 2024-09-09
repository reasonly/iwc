package com.iworkcloud.mapper;

import com.iworkcloud.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 
* Title: UserDao
* Description:
* 用户数据接口 
* Version:1.0.0
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    User findUserByAccount(String account);
    List<User> findUsersByUser(User user);

}
