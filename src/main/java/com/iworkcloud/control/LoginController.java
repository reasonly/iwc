package com.iworkcloud.control;

import com.iworkcloud.pojo.entity.User;
import com.iworkcloud.service.UserService;
import com.iworkcloud.service.AdministratorService;
import com.iworkcloud.pojo.entity.Results;
import com.iworkcloud.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


@RestController
@CrossOrigin(origins = "*")
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private AdministratorService administratorService;

    @RequestMapping("/tologin")
    public String toLogin() {
        return "user/login";
    }
    @RequestMapping("/login")
    public Results login(@RequestBody Map<String, Object> request) {
        String id = (String)request.get("id");
        String password = (String)request.get("password");
        System.out.println(id);
        System.out.println(password);
        if(userService.findByAccount(id)!=null){
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", id);
            String jwt = JwtUtils.GetStringJwt(claims);
            return Results.Success(jwt);
        }else return Results.Error("用户名或密码错误");
    }
//        if("员工".equals(user.getUserAuthority())) {
//            System.out.println("员工");
//            System.out.println(user);
//            Boolean result=userService.userLogin(user);
//            if(result){
//                session.setAttribute("currentUser", userService.findByAccount(user.getUserAccount()));
//                session.setAttribute("Authority", 0);
//
//
//
//               return "/index";
//            }
//            else
//                return "/error";
//        }else {
//            System.out.println("管理员");
//            Administrator admin=new Administrator();
//            admin.setAdministratorAccount(user.getUserAccount());
//            admin.setAdministratorPassword(user.getUserPassword());
//            admin.setAdministratorAuthority(user.getUserAuthority());
//
//            System.out.println(admin);
//
//            Boolean result=administratorService.administratorLogin(admin);
//            session.setAttribute("currentAdministrator", administratorService.findByAccount(admin.getAdministratorAccount()));
//            session.setAttribute("Authority", 1);
//            if(result)
//                return "/index";
//
//            else
//                return "/error";
//
//        }
//        if ("员工".equals(user.getUserAuthority())) {
//            System.out.println("员工");
//            System.out.println(user);
//            Boolean result = userService.userLogin(user);
//            if (result) {
//                session.setAttribute("currentUser", userService.findByAccount(user.getUserAccount()));
//                session.setAttribute("Authority", 0);
//            }
//        }




}