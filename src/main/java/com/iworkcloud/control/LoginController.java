package com.iworkcloud.control;

import com.iworkcloud.pojo.entity.User;
import com.iworkcloud.service.UserService;
import com.iworkcloud.pojo.entity.Administrator;
import com.iworkcloud.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


/**
 * Title: UserController
 * Description:
 * 用户控制层
 * Version:1.0.0
 */
@Controller
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
    public String login(User user, HttpSession session, Model model) {
        if("员工".equals(user.getUserAuthority())) {
            System.out.println("员工");
            System.out.println(user);
            Boolean result=userService.userLogin(user);
            if(result){

                session.setAttribute("currentUser", userService.findByAccount(user.getUserAccount()));
                session.setAttribute("Authority", 0);

                User currentUser = (User) session.getAttribute("currentUser");
                System.out.println(currentUser);
                return "/index";
            }
            else
                return "/error";
        }else {
            System.out.println("管理员");
            Administrator admin=new Administrator();
            admin.setAdministratorAccount(user.getUserAccount());
            admin.setAdministratorPassword(user.getUserPassword());
            admin.setAdministratorAuthority(user.getUserAuthority());
            System.out.println(admin);
            Boolean result=administratorService.administratorLogin(admin);

            if(result)
                return "/index";
            else
                return "/error";

        }

    }


}