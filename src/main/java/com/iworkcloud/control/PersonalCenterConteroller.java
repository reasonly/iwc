package com.iworkcloud.control;

import com.iworkcloud.pojo.ResultCode;
import com.iworkcloud.pojo.entity.Project;
import com.iworkcloud.pojo.entity.User;
import com.iworkcloud.service.AdministratorService;
import com.iworkcloud.service.ProjectService;
import com.iworkcloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import com.iworkcloud.pojo.Result;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import javax.servlet.http.HttpSession;

@Controller

public class PersonalCenterConteroller {

    @Autowired
    private UserService userService1;
    @Autowired
    private AdministratorService administratorService1;

    @RequestMapping("/homePersonalCenter")
    public String toLogin() {
        System.out.println("personalcontroller");
        return "/personalCenter";

    }
//    @RequestMapping("/attendance")
//    public String attendance() {
//        return "/personalCenter";
//
//    }
//    @RequestMapping("/changePassword")
//    public String changePassword() {
//
//        return "/personalCenter";
//    }
//    @RequestMapping("/askLeave")
//    public String askLeave() {
//
//        return "/personalCenter";
//    }

}
