package com.iworkcloud.control;

import com.iworkcloud.pojo.Attendance;
import com.iworkcloud.pojo.Results;
import com.iworkcloud.pojo.User;
import com.iworkcloud.service.AttendanceService;
import com.iworkcloud.service.UserService;
import com.iworkcloud.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/AttendanceController")
public class AttendanceController {

    @Autowired
    private UserService userService;

    @Autowired
    private AttendanceService attendanceService;

    @RequestMapping("/attendanceList")
    public Results personalCenter(HttpServletRequest Request) {

        System.out.println("访问localhost:9000/AttendanceController/attendanceList ！");
        String jwt = Request.getHeader("token");
        System.out.println("解析jwt="+jwt);
        Map<String, Object> claim =JwtUtils.ParseJwt(jwt);
        int id = (int) claim.get("id");
        String authority =(String) claim.get("authority");
        System.out.println("解析令牌得id="+id+" authority="+authority);

        Map<String, Object> claims = new HashMap<>();
        if(authority.equals("管理员")){
            User user=userService.findByPrimaryKey(id);
            claims.put("user", user);
            return Results.Success(claims);
        }else if(authority.equals("员工")){
            User user=userService.findByPrimaryKey(id);
            List<Attendance> attendanceList=attendanceService.findByUserId(id);
            System.out.println("List<Attendance>" + attendanceList);

            claims.put("user", user);
            claims.put("attendanceList", attendanceList);

            return Results.Success(claims);
        }else return Results.Error("出错");
    }
//    @RequestMapping("/attendance")
//    public String attendance() {
//        return "/personalCenter";
//
//    }


//    @RequestMapping("/askLeave")
//    public String askLeave() {
//
//        return "/personalCenter";
//    }

}
