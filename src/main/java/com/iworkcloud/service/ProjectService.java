package com.iworkcloud.service;

import com.iworkcloud.pojo.entity.Project;

public interface ProjectService extends BaseService<Project>{
    boolean delete(Project project);
}
