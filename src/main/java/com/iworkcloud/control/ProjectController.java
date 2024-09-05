package com.iworkcloud.control;

import com.iworkcloud.pojo.ResultCode;
import com.iworkcloud.pojo.entity.Project;
import com.iworkcloud.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import com.iworkcloud.pojo.Result;
import org.springframework.web.bind.annotation.RequestParam;

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
    /**
     * 跳转到编辑页面的请求处理方法
     *
     * 该方法处理/toEdit路径的GET请求，用于跳转到项目编辑页面
     * 它接收一个Model对象和一个参数id，id是需要编辑的项目的标识
     *
     * @param module Model对象，用于向视图传递模型属性
     * @param id 需要编辑的项目的标识
     * @return 返回项目编辑页面的视图名称
     */
    @RequestMapping("/toEdit")
    public String toEdit(Model module, @RequestParam() Integer id) {
        System.out.println("toEdit"+id+"??");
        module.addAttribute("project", projectService.findByPrimaryKey(id));
        return "project/editProject";
    }
    @RequestMapping("/edit")
    public String edit(Project project) {
        System.out.println("edit"+project);
        projectService.update(project);
        return "redirect:/projectc/projectListc";
    }
    @RequestMapping("/delete")
    public String delete(Model module, @RequestParam Integer id) {
        System.out.println("delete"+id);
        projectService.deleteByPrimaryKey(id);
        return "redirect:/projectc/projectListc";
    }
}
