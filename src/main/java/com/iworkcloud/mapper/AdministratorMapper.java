package com.iworkcloud.mapper;

import com.iworkcloud.pojo.entity.Administrator;

public interface AdministratorMapper extends BaseMapper<Administrator>{
    Administrator findByAdministratorAccountAndPassword(Administrator adminstrator);

    String findSaltByAdministratorAccount(String administratoraccount);
}
