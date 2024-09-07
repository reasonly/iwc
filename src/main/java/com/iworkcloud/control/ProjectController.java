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

import java.util.LinkedHashMap;
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

        Integer isAdmin = 1;
        List<Project> projectList=null;
        if(isAdmin==1){
//            User currentAdministrator = (User) session.getAttribute("currentAdministrator");
//            module.addAttribute("currentInfo",currentAdministrator);
            projectList= projectService.projectList();

        }
        else {
//            User currentUser = (User) session.getAttribute("currentUser");
//            module.addAttribute("currentInfo",currentUser);
//            projectList= projectService.projectList(currentUser.getUserId());

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
    @PutMapping("/edit")
    public Results edit(@RequestBody Map<String, Object> request) {

        Project project = getProject(request);
        System.out.println("edit"+project);
        projectService.update(project);
        return Results.Success("编辑成功");
    }
    @DeleteMapping("/delete")
    public Results delete(@RequestBody Map<String, Object> request) {
        Integer id = (Integer) request.get("id");
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
//        LinkedHashMap<String, Object> requestMap = (LinkedHashMap<String, Object>) request.get("project");
//        System.out.println(requestMap.get("projectName"));
        Project project = new Project();
        project.setProjectName((String) request.get("projectName"));
        System.out.println("add"+project);
        projectService.insert(project);
        return Results.Success("成功添加");
    }

    /**
     * 添加员工到项目中
     * @param request projectId,List<> UserId
     * @return
     */
    @PutMapping("/addUser")
    public Results addUser(@RequestBody Map<String, Object> request) {
        System.out.println("addUser");
         Integer projectId = (Integer) request.get("projectId");
         List<Integer> userIdList = (List<Integer>) request.get("userIdList");
         projectService.addUser(projectId,userIdList);
        return Results.Success("成功添加");
    }
    /**
     * 用户身份的搜索
     *
     * @param request:{
     *                 "project":项目
     *                 "userId":  用户数据
     * }
     * @return Results
     */
    @GetMapping("/userSearch")
    public Results search(@RequestBody Map<String, Object> request){
        System.out.println("search");

        Project project = this.getProject(request);
        Integer userId = (Integer) request.get("userId");
        List<Project> projectList=projectService.projectList(project,userId);
        if (projectList.size()==0){
            return Results.Error("未找到");
        }else{
            return Results.Success(projectList);
        }

    }
    @GetMapping("/adminSearch")
    public Results adminSearch(@RequestBody Map<String, Object> request){
        System.out.println("adminSearch");
        Project project = (Project) request.get("project");
        List<Project> projectList=projectService.projectList(project);
        return Results.Success(projectList);
    }
    private Project getProject(Map<String, Object> request){
        LinkedHashMap<String, Object> requestMap = (LinkedHashMap<String, Object>) request.get("project");
        Project project = new Project();
        project.setProjectId((Integer) requestMap.get("projectId"));
        project.setProjectName((String) requestMap.get("projectName"));
        project.setProjectContent((String) requestMap.get("projectContent"));
        project.setProjectState((String) requestMap.get("projectState"));
        project.setAdministratorId((Integer) requestMap.get("administratorId"));
        return project;
    }
}

