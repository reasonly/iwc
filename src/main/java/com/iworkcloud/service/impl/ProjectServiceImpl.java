package com.iworkcloud.service.impl;
import com.iworkcloud.mapper.BaseMapper;
import com.iworkcloud.mapper.ProjectMapper;
import com.iworkcloud.pojo.Project;
import com.iworkcloud.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
@Service
public class ProjectServiceImpl extends BaseServiceImpl<Project> implements ProjectService {
    @Autowired
    private ProjectMapper projectMapper;
    @Override
    protected BaseMapper<Project> getMapper() {
        return this.projectMapper;
    }
    @Override
    public List<Project> projectList(Model model) {
        return projectMapper.findAll();
    }

}
