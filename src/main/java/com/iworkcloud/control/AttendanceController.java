package com.iworkcloud.control;

import com.iworkcloud.pojo.*;
import com.iworkcloud.service.AttendanceService;
import com.iworkcloud.service.UserService;
import com.iworkcloud.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import java.time.temporal.ChronoUnit;
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

    }

    @RequestMapping("/attendanceSelect")
    public Results attendanceSelect(HttpServletRequest Request,@RequestBody Map<String, Object> request) {

        System.out.println("访问localhost:9000/AttendanceController/attendanceSelect ！");

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

        Attendance atd = new Attendance();
        atd.setAttendanceId((Integer) request.get("attendanceId"));
        atd.setUserId((Integer) request.get("userId"));
        atd.setAttendanceState((String) request.get("attendanceState"));
        if(request.get("date")!=null){
            atd.setDate(Date.valueOf(request.get("date").toString()));
        }
        atd.setDeadline((String) request.get("deadline"));
        System.out.println("搜索atd:"+ atd);
        List<Attendance> attendanceList=attendanceService.findAttendancesByAttendance(atd);
        System.out.println("List<Attendance>" + attendanceList);
        Map<String, Object> claims = new HashMap<>();
        claims.put("attendanceList", attendanceList);
        return Results.Success(claims);

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
        atd.setDeadline("否");
        atd=attendanceService.findAttendanceByDateAndUserIdAndDeadline(atd);
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
        }else return Results.Error("目前没有考勤要求！或考勤已截止");
    }


/*---------------------------------------------------自动化考勤管理----------------------------------------------*/

    public static String startTime1="0 30 07 * * ?";// s m h d m w
    public static String endTime1 = "0 30 08 * * ?";
    public static String startTime2="0 30 16 * * ?";
    public static String endTime2 = "0 30 17 * * ?";
    public static String duration ="01:00:00";


    //@Scheduled(cron = WOIA_ID)

//    @Scheduled(cron = "#{startTime1}")
//    @Scheduled(cron = "#{startTime2}")
    //@Scheduled(cron = "0 26 16 * * ?")
    public void scheduleNewAttendance() {
        System.out.println("生成考勤信息！");
        User user =new User();
        user.setUserAuthority("员工");
        List<User> list = userService.findUsersByUser(user);

        Attendance attendance = new Attendance();
        // 日期
        Date date= new Date(System.currentTimeMillis());
        attendance.setDate(date);
        attendance.setDeadline("否");
        for (User a : list) {
            attendance.setUserId(a.getUserId());
            attendance.setAttendanceState("未签到"); // 初始状态，根据实际业务逻辑设置
            attendanceService.insert(attendance);
        }
    }

    //@Scheduled(cron = "#{endTime1}") // s m h d m w
    //@Scheduled(cron = "#{endTime2}")
    //@Scheduled(cron = "0 26 16 * * ?")
    public void scheduleUpdateAttendance() {
        System.out.println("考勤截止！");
        Attendance atd = new Attendance();
        // 日期
        Date date= new Date(System.currentTimeMillis());
        atd.setDate(date);
        atd.setDeadline("否");
        List<Attendance> list= attendanceService.findAttendancesByAttendance(atd);
        //Attendance attendance = new Attendance();

        for (Attendance attendance : list) {
            attendance.setDeadline("是");
            attendanceService.attendanceByAttendanceId(attendance);
        }
    }
    @Scheduled(cron = "0 0 0 1 * ?") // 每月1号，清除三个月前考勤信息
    //@Scheduled(cron = "0 41 19 * * ?")
    public void scheduleDeleteAttendance() {
        System.out.println("清除3月前的考勤信息！");
        attendanceService.deleteThreeMonthsBefore();

    }
//    @RequestMapping("/TimeChange")
//    public Results TimeChange(HttpServletRequest Request,@RequestBody Map<String, Object> request) {
//        int id = 0;
//        System.out.println("访问localhost:9000/AttendanceController/TimeChange !");
//        try{
//            String jwt = Request.getHeader("token");
//            System.out.println("解析jwt="+jwt);
//            Map<String, Object> claim =JwtUtils.ParseJwt(jwt);
//            id = (int) claim.get("id");
//            System.out.println("id :"+id);
//        }catch (Exception e){
//
//            return Results.Error("token过期，请重新登录！");
//        }
//
//        String startTime1 = (String)request.get("startTime1");
//        String startTime2 = (String)request.get("startTime2");
//        String endTime1=null;
//        String endTime2=null;
//
//        String duration=(String)request.get("duration");
//        if(duration==null){
//            duration=this.duration;
//        }
//
//        if(startTime1 !=null){
//            endTime1 = addTime(startTime1,duration);
//        }
//        if(startTime2 != null){
//            endTime2 = addTime(startTime2,duration);
//        }
//
//        try{
//            if(startTime1 != null){
//                this.startTime1 =convertToCronExpression(startTime1);
//            }
//            if (startTime2!=null){
//                this.startTime2=convertToCronExpression(startTime2);
//            }
//            if (endTime1 !=null){
//                this.endTime1 =convertToCronExpression(endTime1);
//            }
//            if (endTime2 !=null){
//                this.endTime2=convertToCronExpression(endTime2);
//            }
//        }catch(Exception e){
//            System.out.println(e);
//            return Results.Error();
//        }
//        return Results.Success();
//    }

    //将用户输入的时间（格式为 hh:MM:ss）转换为 @Scheduled 注解所需的 cron 表达式格式（例如 "0 30 07 * * ?"）
    public static String convertToCronExpression(String userInput) {
        // 定义时间格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        // 解析用户输入的时间
        LocalTime userTime = LocalTime.parse(userInput, formatter);

        // 构建cron表达式，这里假设每天的这个时间执行
        String cronExpression = String.format("0 %d %d * * ?", userTime.getMinute(), userTime.getHour());

        return cronExpression;
    }

    public static String addTime(String startTime1, String duration) {
        // 定义时间格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        // 解析开始时间和持续时间
        LocalTime start = LocalTime.parse(startTime1, formatter);
        LocalTime durationTime = LocalTime.parse(duration, formatter);

        // 计算结束时间
        LocalTime endTime = start.plus(durationTime.getHour(), ChronoUnit.HOURS)
                .plus(durationTime.getMinute(), ChronoUnit.MINUTES)
                .plus(durationTime.getSecond(), ChronoUnit.SECONDS);

        // 如果结束时间超过24小时，则减去24小时
        if (endTime.isAfter(start.plusHours(24))) {
            endTime = endTime.minusHours(24);
        }

        // 返回结果时间
        return endTime.format(formatter);
    }

}
