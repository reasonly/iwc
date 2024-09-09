package com.iworkcloud.control;

import com.iworkcloud.pojo.Project;
import com.iworkcloud.pojo.Results;
import com.iworkcloud.pojo.User;
import com.iworkcloud.service.ProjectService;
import com.iworkcloud.util.JwtUtils;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import javax.persistence.Tuple;
import javax.servlet.http.HttpServletRequest;

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
        int id = 0;
        String authority ="";
        try{
            String jwt = Request.getHeader("token");
            System.out.println("解析jwt="+jwt);
            Map<String, Object> claim =JwtUtils.ParseJwt(jwt);
            id = (int) claim.get("id");
            authority =(String) claim.get("authority");
            System.out.println("解析令牌得id="+id+" authority="+authority);
        }catch (Exception e){
            return Results.Error("token过期，请重新登录！");
        }
        System.out.println("authority:"+authority);

        List<Project> projectList=null;
        if("管理员".equals(authority)){
            projectList= projectService.projectList();

        }
        else {
               projectList= projectService.projectList(id);

        }

        if(projectList.size()==0) {
            return Results.Error("暂无项目");
        }
        return Results.Success(projectList);
    }

    @PutMapping("/edit")
    public Results edit(@RequestBody Map<String, Object> request) {
        Project project = getProject(request);
        System.out.println("edit"+project);
        if(projectService.update(project)){
            return Results.Success("编辑成功");
        }
        return Results.Error("编辑失败");
    }
    @DeleteMapping("/delete")
    public Results delete(@RequestBody Map<String, Object> request) {
        Integer projectId = (Integer) request.get("projectId");
        System.out.println("delete"+projectId);
        if(projectService.deleteByPrimaryKey(projectId)){
            return Results.Success("成功删除");
        }
        return Results.Error("删除失败");
    }

    @PutMapping("/add")
    public Results add(@RequestBody Map<String, Object> request, HttpServletRequest Request) {
        Project project =getProject(request);

        Pair<Integer, String> pair= getUserIdAndAuthority(Request);
        Integer userId = pair.getKey();

        project.setUserId(userId);

        System.out.println("add"+project);
        if(projectService.insert(project)){
        return Results.Success("项目添加成功");
        }
        return Results.Error("项目添加失败");
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
         if(projectService.addUser(projectId,userIdList)) {
             return Results.Success("成功添加员工到该项目");
         }
         return Results.Error("添加失败");
    }
    /**
     * 用户身份的搜索
     *
     * @param request
     */
    @GetMapping("/search")
    public Results search(HttpServletRequest Request,@RequestBody Map<String, Object> request){
        System.out.println("search");
        Project project = getProject(request);

        Pair<Integer, String> pair= getUserIdAndAuthority(Request);
        Integer id = pair.getKey();
        String authority = pair.getValue();
        List<Project> projectList= null;
        if("管理员".equals(authority)){
            projectList=projectService.projectList(project);
        }else {
            projectList=projectService.projectList(project,id);
        }
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
        if (projectList.size()==0){
            return Results.Error("未找到");
        }else{
            return Results.Success(projectList);
        }
    }

    @GetMapping("/userInProject")
    public Results userInProject(@RequestBody Map<String, Object> request){
        System.out.println("userInProject");
        Integer projectId = (Integer) request.get("projectId");
        List<User> userList=projectService.findUsersInProject(projectId,"员工");
        if (userList.size()!=0){
        return Results.Success(userList);
        }
        return Results.Error("没有参加该项目的员工");
    }
    @GetMapping("/userNotInProject")
    public Results userNotInProject(@RequestBody Map<String, Object> request){
        System.out.println("userNotInProject");
        Integer projectId = (Integer) request.get("projectId");
        List<User> userList = projectService.findUsersNotInProject(projectId,"员工");
        if (userList.size()!=0){
            return Results.Success(userList);
        }
        return Results.Error("所有员工都参加了");
    }
    private Project getProject(Map<String, Object> request){
        return new Project((Integer)request.get("projectId"), (String)request.get("projectName"), (String)request.get("projectContent"), (String)request.get("projectState"), (Integer)request.get("userId"), (Double) request.get("projectTotal"));
    }
    private Pair<Integer, String> getUserIdAndAuthority(HttpServletRequest Request){
        String jwt = Request.getHeader("token");
        Map<String, Object> claim = JwtUtils.ParseJwt(jwt);
        Pair<Integer, String> pair = new Pair<>((Integer) claim.get("id"), (String)claim.get("authority"));
        return pair;
    }

}

