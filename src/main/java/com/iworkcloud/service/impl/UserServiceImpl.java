package com.iworkcloud.service.impl;

import com.iworkcloud.mapper.BaseMapper;
import com.iworkcloud.mapper.UserMapper;
import com.iworkcloud.pojo.entity.User;
import com.iworkcloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iworkcloud.util.MD5Util;

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
    public Boolean userLogin(User user) {
        System.out.println("UserServiceImpl.userLogin");
        try {
            // 获取用户的盐值
            String salt=userMapper.findSaltByUserAccount(user.getUserAccount());
            // 转为加密密码
            String dbPass=MD5Util.inputPassToDBPass(user.getUserPassword(),salt);

            // 填充加密的密码，在数据库中进行比对
            user.setUserPassword(dbPass);
            User user1 = userMapper.findByUsernameAndPassword(user);
            System.out.println(user1);
            return user1 != null;
        } catch (Exception e) {
            System.out.println("查询失败!");
            e.printStackTrace();
        }
        return false;
    }


}
