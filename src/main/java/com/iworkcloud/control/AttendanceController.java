package com.iworkcloud.control;

import com.iworkcloud.pojo.*;
import com.iworkcloud.service.AttendanceService;
import com.iworkcloud.service.LeaveService;
import com.iworkcloud.service.UserService;
import com.iworkcloud.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public Results attendanceList(HttpServletRequest Request) {

        System.out.println("访问localhost:9000/AttendanceController/attendanceList ！");

        int id = 0;
        try{
            String jwt = Request.getHeader("token");
            System.out.println("解析jwt="+jwt);
            Map<String, Object> claim =JwtUtils.ParseJwt(jwt);
            id = (int) claim.get("id");
            String authority =(String) claim.get("authority");
            System.out.println("解析令牌得id="+id+" authority="+authority);
        }catch (Exception e){
            return Results.Error("token过期，请重新登录！");
        }

        User user=userService.findByPrimaryKey(id);
        Map<String, Object> claims = new HashMap<>();
        claims.put("user", user);
        List<Attendance> attendanceList=attendanceService.findAllByUserId(id);
        System.out.println("List<Attendance>" + attendanceList);
        claims.put("attendanceList", attendanceList);
        return Results.Success(claims);

//        if(authority.equals("管理员")){
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

        int id = 0;
        System.out.println("访问localhost:9000/AttendanceController/attendance !");
        try{
            String jwt = Request.getHeader("token");
            System.out.println("解析jwt="+jwt);
            Map<String, Object> claim =JwtUtils.ParseJwt(jwt);
            id = (int) claim.get("id");
            System.out.println("id :"+id);

        }catch (Exception e){

            return Results.Error("token过期，请重新登录！");
        }

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
        atd.setDate(date);
        atd=attendanceService.findAttendanceByDateAndUserId(atd);
        System.out.println("当天考勤："+atd);
        if(atd != null){
            if(atd.getAttendanceState().equals("未签到"))
            {
                atd.setAttendanceTime(datetime);
                atd.setAttendanceState("已签到");
                System.out.println("修改考勤信息预览："+atd);
                boolean isAttendance = attendanceService.attendanceByAttendanceId(atd);
                System.out.println(isAttendance);
                if(isAttendance)
                    return Results.Success("签到成功");
                else return Results.Error("签到失败！");
            }else return Results.Error("今日签到已处理！不要重复签到！");
        }else return Results.Error("今天没有考勤要求！");
    }



}
