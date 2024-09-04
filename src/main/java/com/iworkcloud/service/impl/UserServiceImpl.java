package com.iworkcloud.service.impl;

import com.iworkcloud.mapper.BaseMapper;
import com.iworkcloud.mapper.UserMapper;
import com.iworkcloud.pojo.entity.User;
import com.iworkcloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Title: UserServiceImpl
 * Description:
 * 用户操作实现类
 * Version:1.0.0
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
    //mybatis
    @Autowired
    private UserMapper userMapper;

    @Override
    protected BaseMapper<User> getMapper() {
        return this.userMapper;
    }

    /**
     * @param user 用户实体
     * @return 查询结果
     */
    @Override
    public Boolean findByUsernameAndPassword(User user) {
        try {
            System.out.println("findByUsernameAndPassword");
            User user1 = userMapper.findByUsernameAndPassword(user);
            return user1 != null;
        } catch (Exception e) {
            System.out.println("查询失败!");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User findByUsername(User username) {
        return userMapper.findByUsername(username);
    }

}
