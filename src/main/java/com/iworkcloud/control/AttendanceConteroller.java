package com.iworkcloud.control;

import com.iworkcloud.pojo.Result;
import com.iworkcloud.pojo.ResultCode;
import com.iworkcloud.pojo.entity.Administrator;
import com.iworkcloud.pojo.entity.Attendance;
import com.iworkcloud.pojo.entity.User;
import com.iworkcloud.service.AttendanceService;
import com.iworkcloud.service.AdministratorService;
import com.iworkcloud.service.UserService;
import com.iworkcloud.util.MD5Util;
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
        if(0==authority){
            System.out.println("员工个人中心");

            User currentUser = (User) session.getAttribute("currentUser");

            List<Attendance> AttendanceList=attendanceService.findByUserId(currentUser.getUserId());
            Result<List<Attendance>> result = new Result<>(ResultCode.SUCCESS, AttendanceList);


            module.addAttribute("resultList",result);
            module.addAttribute("user",currentUser);


            return "personalcenter/personalCenter";
        }else{
            System.out.println("管理员个人中心");
            Administrator currentAdmin = (Administrator) session.getAttribute("currentAdministrator");
            module.addAttribute("admin",currentAdmin);

            return "personalcenter/personalCenter";
        }



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
        if(0==authority){
            System.out.println("员工AttendanceList");

            User currentUser = (User) session.getAttribute("currentUser");
            module.addAttribute("user",currentUser);


            return "personalcenter/personalCenter";
        }else{
            System.out.println("管理员个人中心");
            Administrator currentAdmin = (Administrator) session.getAttribute("currentAdministrator");
            module.addAttribute("admin",currentAdmin);

            return "personalcenter/personalCenter";
        }
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

        else{
            System.out.println("管理员个人中心");
            Administrator currentAdmin = (Administrator) session.getAttribute("currentAdministrator");
            //module.addAttribute("admin",currentAdmin);

            return "personalcenter/personalCenter";
        }



        return "/personalCenter";
    }
//    @RequestMapping("/askLeave")
//    public String askLeave() {
//
//        return "/personalCenter";
//    }

}
