package com.iworkcloud.service;

import com.iworkcloud.pojo.entity.Administrator;

public interface AdministratorService  extends BaseService<Administrator> {

    Boolean findByAdministratorAccountAndPassword(Administrator administrator);

    Boolean administratorLogin(Administrator user);
}
