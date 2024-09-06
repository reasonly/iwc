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


@Controller
@RequestMapping
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
    public String login(User user, HttpSession session) {
        if("员工".equals(user.getUserAuthority())) {
            System.out.println("员工");
            System.out.println(user);
            Boolean result=userService.userLogin(user);
            if(result){
                session.setAttribute("currentUser", userService.findByAccount(user.getUserAccount()));
                session.setAttribute("Authority", 0);
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
            session.setAttribute("currentAdministrator", administratorService.findByAccount(user.getUserAccount()));
            session.setAttribute("Authority", 1);
            if(result)
                return "/index";
            else
                return "/error";

        }

    }


}