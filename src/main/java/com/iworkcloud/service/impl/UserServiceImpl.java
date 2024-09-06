package com.iworkcloud.service.impl;

import com.iworkcloud.mapper.BaseMapper;
import com.iworkcloud.mapper.UserMapper;
import com.iworkcloud.pojo.User;
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
    public Boolean cheakUserPassword(User user,String password) {
        System.out.println("开始检测用户密码!");
        try {
            // 获取用户的盐值
            String salt=user.getUserSalt();
            // 将用户密码转为加密密码
            String dbPass=MD5Util.inputPassToDBPass(password,salt);
            // 加密的密码，与数据库中进行比对
            if(dbPass.equals(user.getUserPassword())){
                System.out.println("密码对比正确!");
                return true;
            }else {
                System.out.println("密码对比不一致!");
                return false;
            }
        } catch (Exception e) {
            System.out.println("密码对比过程出错!");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User findUserByAccount(String str) {
        User user = null;
        try {
            user = userMapper.findUserByAccount(str);
        } catch (Exception e) {
            System.out.println("该用户名查询失败!");
            e.printStackTrace();
        }
        return user;
    }

}
