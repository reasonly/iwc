package com.iworkcloud.service;

import com.iworkcloud.pojo.Project;
import org.springframework.ui.Model;

import java.util.List;

public interface ProjectService extends BaseService<Project>{
    List<Project> projectList(Model model);
}
