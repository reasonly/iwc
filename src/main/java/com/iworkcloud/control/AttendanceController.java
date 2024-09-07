package com.iworkcloud.control;

import com.iworkcloud.pojo.Attendance;
import com.iworkcloud.pojo.Results;
import com.iworkcloud.pojo.User;
import com.iworkcloud.service.AttendanceService;
import com.iworkcloud.service.UserService;
import com.iworkcloud.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
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
        User user=userService.findByPrimaryKey(id);

        Map<String, Object> claims = new HashMap<>();
        claims.put("user", user);
        List<Attendance> attendanceList=attendanceService.findAllByUserId(id);
        System.out.println("List<Attendance>" + attendanceList);
        claims.put("attendanceList", attendanceList);
        return Results.Success(claims);

//        if(authority.equals("管理员")){
//
//
//            return Results.Success(claims);
//        }else if(authority.equals("员工")){
//
//            List<Attendance> attendanceList=attendanceService.findAllByUserId(id);
//            System.out.println("List<Attendance>" + attendanceList);
//            claims.put("attendanceList", attendanceList);
//
//            return Results.Success(claims);
//        }else return Results.Error("出错");
    }
    @RequestMapping("/attendance")
    public Results attendance(HttpServletRequest Request) {

        System.out.println("访问localhost:9000/AttendanceController/attendance !");
        String jwt = Request.getHeader("token");
        System.out.println("解析jwt="+jwt);
        Map<String, Object> claim =JwtUtils.ParseJwt(jwt);
        int id = (int) claim.get("id");
        System.out.println("id  " + id);

        // 日期
        Date date= new Date(System.currentTimeMillis());
        System.out.println("日期："+date);

        //时间
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String time =LocalDateTime.now().format(formatter);
        Timestamp datetime = Timestamp.valueOf(time);
        System.out.println("时间：" +datetime);

        Attendance atd = new Attendance();
        atd.setUserId(id);
        atd.setAttendanceState("已签到");
        atd.setAttendanceTime(datetime);
        atd.setDate(date);

        System.out.println(atd);
        boolean isAttendance =attendanceService.attendanceByDateAndUserId(atd);
        System.out.println("isAttendance="+isAttendance);

        return Results.Success();

    }


//    @RequestMapping("/askLeave")
//    public String askLeave() {
//
//        return "/personalCenter";
//    }

}
