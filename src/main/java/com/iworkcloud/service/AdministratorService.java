package com.iworkcloud.service;

import com.iworkcloud.pojo.entity.Administrator;

public interface AdministratorService extends BaseService<Administrator> {
    Boolean administratorLogin(Administrator administrator);

}
