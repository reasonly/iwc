package com.iworkcloud.service.impl;
import com.iworkcloud.mapper.BaseMapper;
import com.iworkcloud.mapper.ProjectMapper;
import com.iworkcloud.pojo.Project;
import com.iworkcloud.pojo.User;
import com.iworkcloud.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProjectServiceImpl extends BaseServiceImpl<Project> implements ProjectService {
    @Autowired
    private ProjectMapper projectMapper;
    @Override
    protected BaseMapper<Project> getMapper() {
        return this.projectMapper;
    }
    @Override
    public List<Project> projectList() {
        return projectMapper.findAll();
    }

    @Override
    public List<Project> projectList(Integer id){
        return projectMapper.findByUserId(id);
    }
    @Override
    public List<Project> projectList(Project project){
        return projectMapper.findByListEntity(project);
    }
    @Override
    public List<Project> projectList(Project project, Integer id){
        return projectMapper.findByListEntityAndUserId(project,id);
    }
    @Override
    public boolean addUser(Integer projectId,List<Integer> userIdList){
        return projectMapper.addUser(projectId,userIdList);
    }



    @Override
    public List<User> findUsersNotInProject(Integer projectId, String authority){

        return projectMapper.findUsersNotInProject(projectId, authority);
    }

    @Override
    public List<User> findUsersInProject(Integer projectId,String authority){
        return projectMapper.findUsersInProject(projectId, authority);
    }
}
