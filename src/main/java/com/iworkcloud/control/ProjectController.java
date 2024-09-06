package com.iworkcloud.control;

import com.iworkcloud.pojo.Project;
import com.iworkcloud.pojo.Results;
import com.iworkcloud.pojo.User;
import com.iworkcloud.service.ProjectService;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import com.iworkcloud.pojo.Results;

import java.util.List;
import java.util.Map;
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

//    @RequestMapping("/toEdit")/*----------------*/
//    public String toEdit(Model module, @RequestParam() Integer id) {
//        System.out.println("toEdit"+id+"??");
//
//        module.addAttribute("project", projectService.findByPrimaryKey(id));
//        return "project/editProject";
//    }
    @Update("/edit")
    public Results edit(@RequestBody Map<String, Object> request) {
        Project project = (Project) request.get("project");
        System.out.println("edit"+project);
        projectService.update(project);
        return Results.Success("编辑成功");
    }
    @DeleteMapping("/delete")
    public Results delete(Model module, @RequestParam Integer id) {
        System.out.println("delete"+id);
        projectService.deleteByPrimaryKey(id);
        return Results.Success("成功删除");
    }

//    @RequestMapping("/toAdd")
//    public String toAdd(Model module,@RequestParam Integer id) {
//        System.out.println("toAdd");
//        Project project = new Project();
//        project.setAdministratorId(id);
//        project.setProjectState("未开始");
//        module.addAttribute("project", project);
//        return "project/addProject";
//    }

    @PutMapping("/add")
    public Results add(@RequestBody Map<String, Object> request) {
        Project project = (Project) request.get("project");

        System.out.println("add"+project);
        projectService.insert(project);
        return Results.Success("成功删除");
    }
    @GetMapping("/userSearch")
    public Results search(@RequestBody Map<String, Object> request){
        System.out.println("search");
        Project project = (Project) request.get("project");
        Integer userId = (Integer) request.get("userId");
        List<Project> projectList=projectService.projectList(project,userId);
        return Results.Success(projectList);
    }
    @GetMapping("/adminSearch")
    public Results adminSearch(@RequestBody Map<String, Object> request){
        System.out.println("adminSearch");
        Project project = (Project) request.get("project");
        List<Project> projectList=projectService.projectList(project);
        return Results.Success(projectList);
    }
}
