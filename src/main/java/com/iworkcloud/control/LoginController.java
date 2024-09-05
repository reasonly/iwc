package com.iworkcloud.control;

import com.iworkcloud.pojo.entity.User;
import com.iworkcloud.service.UserService;
import com.iworkcloud.pojo.entity.Administrator;
import com.iworkcloud.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


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
    private AdministratorService administratorService;

    @RequestMapping("/login")
    public String login(User user) {
        System.out.println(user);

        if(user.getUserAuthority()=="员工") {
            Boolean result=userService.userLogin(user);

            if(result)
                return "/index";
            else
                return "user/login";
        }else {
            Administrator admin=new Administrator();
            admin.setAdministratorAccount(user.getUserAccount());
            admin.setAdministratorPassword(user.getUserPassword());
            admin.setAdministratorAuthority(user.getUserAuthority());
            Boolean result=administratorService.administratorLogin(admin);

            if(result)
                return "/index";
            else
                return "user/login";

        }

    }


}