package com.iworkcloud.service.impl;


import com.iworkcloud.mapper.BaseMapper;
import com.iworkcloud.mapper.AdministratorMapper;
import com.iworkcloud.pojo.entity.Administrator;
import com.iworkcloud.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import com.iworkcloud.util.MD5Util;



public class AdministratorServiceImpl extends BaseServiceImpl<Administrator> implements AdministratorService {

    @Autowired
    private AdministratorMapper administratorMapper;

    @Override
    protected BaseMapper<Administrator> getMapper() {
        return this.administratorMapper;
    }

    /**
     * @param administrator 用户实体
     * @return 查询结果
     */
    @Override
    public Boolean findByAdministratorAccountAndPassword(Administrator administrator) {
        try {
            System.out.println("findByAdministratornameAndPassword");
            Administrator administrator1 = administratorMapper.findByAdministratorAccountAndPassword(administrator);
            return administrator1 != null;
        } catch (Exception e) {
            System.out.println("查询失败!");
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public Boolean administratorLogin(Administrator administrator) {
        System.out.println("AdministratorServiceImpl.administratorLogin");
        try {
            // 获取用户的盐值
            String salt=administratorMapper.findSaltByAdministratorAccount(administrator.getAdministratorAccount());
            // 转为加密密码
            String dbPass=MD5Util.inputPassToDBPass(administrator.getAdministratorPassword(),salt);

            // 填充加密的密码，在数据库中进行比对
            administrator.setAdministratorPassword(dbPass);
            Administrator administrator1 = administratorMapper.findByAdministratorAccountAndPassword(administrator);
            System.out.println(administrator1);
            return administrator1 != null;
        } catch (Exception e) {
            System.out.println("查询失败!");
            e.printStackTrace();
        }
        return false;
    }


}
