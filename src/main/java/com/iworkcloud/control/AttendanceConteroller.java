package com.iworkcloud.control;

import com.iworkcloud.pojo.User;
import com.iworkcloud.service.AttendanceService;
import com.iworkcloud.service.AdministratorService;
import com.iworkcloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;
import javax.servlet.http.HttpSession;

@Controller

public class AttendanceConteroller {

    @Autowired
    private UserService userService;
    @Autowired
    private AdministratorService administratorService;
    @Autowired
    private AttendanceService attendanceService;

    @RequestMapping("/homePersonalCenter")
    public String topersonalCenter(Model module,HttpSession session) {

        int authority = (int) session.getAttribute("Authority");
        module.addAttribute("authority",authority);

            System.out.println("员工个人中心");

            User currentUser = (User) session.getAttribute("currentUser");


            return "personalcenter/personalCenter";




    }
//    @RequestMapping("/attendance")
//    public String attendance() {
//        return "/personalCenter";
//
//    }

    @RequestMapping("/toEditPassword")
    public String toEditPassword(Model module,HttpSession session) {
        System.out.println("toEditPassword");
        int authority = (int) session.getAttribute("Authority");

            System.out.println("员工AttendanceList");

            User currentUser = (User) session.getAttribute("currentUser");
            module.addAttribute("user",currentUser);


            return "personalcenter/personalCenter";

    }

    @RequestMapping("/editPassword")
    public String editPassword(HttpSession session,@RequestParam() String oldpassword,@RequestParam() String newpassword) {

        int authority = (int) session.getAttribute("Authority");
        if(0==authority) {
            User currentUser = (User) session.getAttribute("currentUser");
            currentUser.setUserPassword(oldpassword);
            if(userService.userLogin(currentUser)){
                System.out.println("旧密码正确");
                currentUser.setUserPassword(newpassword);
                userService.update(currentUser);

            }

        }





        return "/personalCenter";
    }
//    @RequestMapping("/askLeave")
//    public String askLeave() {
//
//        return "/personalCenter";
//    }

}
