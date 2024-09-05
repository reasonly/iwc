package com.iworkcloud.mapper;

import com.iworkcloud.pojo.entity.Administrator;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdministratorMapper extends BaseMapper<Administrator>{

    Administrator findByAdministratorAccountAndPassword(Administrator adminstrator);


    String findSaltByAdministratorAccount(String administratoraccount);
}
