package com.iworkcloud.control;

import com.iworkcloud.pojo.Project;
import com.iworkcloud.pojo.Results;
import com.iworkcloud.pojo.User;
import com.iworkcloud.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import com.iworkcloud.pojo.Results;

import java.util.List;
import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @GetMapping("/List")
    public Results projectListPage(Model module,HttpSession session) {
        System.out.println("projectList");
        System.out.println(session.getAttribute("Authority"));

        Integer isAdmin = (Integer) session.getAttribute("Authority");
        List<Project> projectList=null;
        if(isAdmin==1){
            User currentAdministrator = (User) session.getAttribute("currentAdministrator");
            module.addAttribute("currentInfo",currentAdministrator);
            projectList= projectService.projectList();

        }
        else {
            User currentUser = (User) session.getAttribute("currentUser");
            module.addAttribute("currentInfo",currentUser);
            projectList= projectService.projectList(currentUser.getUserId());

        }


        return Results.Success(projectList);
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

    @RequestMapping("/toAdd")
    public String toAdd(Model module,@RequestParam Integer id) {
        System.out.println("toAdd");
        Project project = new Project();
        project.setAdministratorId(id);
        project.setProjectState("未开始");
        module.addAttribute("project", project);
        return "project/addProject";
    }

    @RequestMapping("/add")
    public String add(@ModelAttribute("project") Project project) {
        System.out.println("add"+project);
        projectService.insert(project);
        return "redirect:/projectc/projectListc";
    }
    @PostMapping("/userSearch")
    public String search(Model model,@RequestParam Project project,@RequestParam Integer userId){
        System.out.println("search");

        List<Project> projectList=projectService.projectList(project,userId);
        model.addAttribute("resultList",projectList);

        return "project/projectList";
    }
    @PostMapping("/adminSearch")
    public String adminSearch(Model model){
        System.out.println("adminSearch");
        return "project/projectList";
    }
}
