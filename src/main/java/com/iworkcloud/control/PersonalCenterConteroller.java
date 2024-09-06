package com.iworkcloud.control;

import com.iworkcloud.pojo.Result;
import com.iworkcloud.pojo.ResultCode;
import com.iworkcloud.pojo.entity.Attendance;
import com.iworkcloud.pojo.entity.User;
import com.iworkcloud.service.AttendanceService;
import com.iworkcloud.service.AdministratorService;
import com.iworkcloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;


import java.util.List;
import javax.servlet.http.HttpSession;

@Controller

public class PersonalCenterConteroller {

    @Autowired
    private UserService userService;
    @Autowired
    private AdministratorService administratorService;
    @Autowired
    private AttendanceService attendanceService;

    @RequestMapping("/homePersonalCenter")
    public String topersonalCenter(Model module,HttpSession session) {

        int authority = (int) session.getAttribute("Authority");
        if(0==authority){
            System.out.println("员工AttendanceList");
            User currentUser = (User) session.getAttribute("currentUser");
            List<Attendance> AttendanceList=attendanceService.findByUserId(currentUser.getUserId());
            Result<List<Attendance>> result = new Result<>(ResultCode.SUCCESS, AttendanceList);
            module.addAttribute("resultList",result);

            return "personalcenter/personalCenter";
        }

        return "personalcenter/personalCenter";

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
