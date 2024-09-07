package com.iworkcloud.control;

import com.iworkcloud.pojo.Results;
import com.iworkcloud.pojo.User;
import com.iworkcloud.service.AttendanceService;
import com.iworkcloud.service.UserService;
import com.iworkcloud.util.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class AttendanceConteroller {

    @Autowired
    private UserService userService;

    @Autowired
    private AttendanceService attendanceService;

    @RequestMapping("/attendanceList")
    public Results topersonalCenter(HttpServletRequest Request) {
        String jwt = Request.getHeader("token");
        Map<String, Object> claim =JwtUtils.ParseJwt(jwt);
        int id = (int) claim.get("id");
        String authority =(String) claim.get("authority");

        System.out.println("解析令牌得id="+id+" authority="+authority);
        if(authority.equals("管理员")){
            User user=userService.findByPrimaryKey(id);

            return Results.Success(user);
        }return Results.Error();

        //attendanceService.findAttendanceById();



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
//            if(userService.userLogin(currentUser)){
//                System.out.println("旧密码正确");
//                currentUser.setUserPassword(newpassword);
//                userService.update(currentUser);
//
//            }

        }





        return "/personalCenter";
    }
//    @RequestMapping("/askLeave")
//    public String askLeave() {
//
//        return "/personalCenter";
//    }

}
