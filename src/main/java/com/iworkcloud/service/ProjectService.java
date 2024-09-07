package com.iworkcloud.service;

import com.iworkcloud.pojo.Project;
import org.springframework.ui.Model;

import java.util.List;

public interface ProjectService extends BaseService<Project>{
    List<Project> projectList();
    List<Project> projectList(Integer id);
    List<Project> projectList(Project project);
    List<Project> projectList(Project project,Integer id);
    boolean addUser(Integer projectId,List<Integer> id);
}
