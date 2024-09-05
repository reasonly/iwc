package com.iworkcloud.control;

import com.iworkcloud.pojo.ResultCode;
import com.iworkcloud.pojo.entity.Project;
import com.iworkcloud.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import com.iworkcloud.pojo.Result;
import java.util.List;

@Controller
@RequestMapping("/projectc")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @RequestMapping("/projectListc")
    public String projectListPage(Model module) {
        System.out.println("projectList");
        List<Project> projectList= projectService.findAll();
        Result<List<Project>> result = new Result<>(ResultCode.SUCCESS, projectList);
        module.addAttribute("resultList",result);

       return "project/projectList";
    }
}
