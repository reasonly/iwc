package com.iworkcloud.control;

import com.iworkcloud.pojo.Project;
import com.iworkcloud.pojo.Results;
import com.iworkcloud.pojo.User;
import com.iworkcloud.service.ProjectService;
import com.iworkcloud.util.JwtUtils;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import com.iworkcloud.pojo.Results;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @GetMapping("/list")
    public Results projectListPage(HttpServletRequest Request) {
        System.out.println("projectList");
        //
        String jwt = Request.getHeader("token");
        Map<String, Object> claim = JwtUtils.ParseJwt(jwt);
        int id = (int) claim.get("id");
        String authority = "员工";
        if (claim.get("authority")!=null) {
            authority = (String) claim.get("authority");
        }
        System.out.println("authority:"+authority);

        List<Project> projectList=null;
        if("管理员".equals(authority)){
//            User currentAdministrator = (User) session.getAttribute("currentAdministrator");

            projectList= projectService.projectList();

        }
        else {
//            User currentUser = (User) session.getAttribute("currentUser");
//            module.addAttribute("currentInfo",currentUser);
               projectList= projectService.projectList(id);

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
        Integer projectId = (Integer) request.get("projectId");
        System.out.println("delete"+projectId);
        projectService.deleteByPrimaryKey(projectId);
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
        Project project =getProject(request);
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
    public Results search(HttpServletRequest Request,@RequestBody Map<String, Object> request){
        System.out.println("search");
        Project project = getProject(request);

        String jwt = Request.getHeader("token");
        Map<String, Object> claim = JwtUtils.ParseJwt(jwt);
        int id = (int) claim.get("id");

        List<Project> projectList=projectService.projectList(project,id);
        if (projectList.size()==0){
            return Results.Error("未找到");
        }else{
            return Results.Success(projectList);
        }

    }
    @GetMapping("/adminSearch")
    public Results adminSearch(@RequestBody Map<String, Object> request){
        System.out.println("adminSearch");
        Project project = getProject(request);
        List<Project> projectList=projectService.projectList(project);
        return Results.Success(projectList);
    }

    private Project getProject(Map<String, Object> request){

        return new Project((Integer)request.get("projectId"), (String)request.get("projectName"), (String)request.get("projectContent"), (String)request.get("projectState"), (Integer)request.get("userId"), (Double) request.get("projectTotal"));
    }
}

