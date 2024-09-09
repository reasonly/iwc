package com.iworkcloud.service;

import com.iworkcloud.pojo.Project;
import com.iworkcloud.pojo.User;
import org.springframework.ui.Model;

import java.util.List;

public interface ProjectService extends BaseService<Project>{
    List<Project> projectList();
    List<Project> projectList(Integer id);
    List<Project> projectList(Project project);
    List<Project> projectList(Project project,Integer id);
    boolean addUser(Integer projectId,List<Integer> id);
    List<User> findUsersNotInProject(Integer projectId,String authority);

    List<User> findUsersInProject(Integer projectId,String authority);
}
