package com.iworkcloud.control;

import com.iworkcloud.mapper.MeetingMapper;
import com.iworkcloud.pojo.*;
import com.iworkcloud.service.AttendanceService;
import com.iworkcloud.service.MeetingService;
import com.iworkcloud.service.UserService;
import com.iworkcloud.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.iworkcloud.control.MeetingController;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/AttendanceController")
public class AttendanceController {

    @Autowired
    private UserService userService;
    @Autowired
    private AttendanceService attendanceService;
    @Autowired
    private MeetingService meetingService;

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

    public static LocalTime duration =LocalTime.parse("01:00:00");
    public static LocalTime startTime1=LocalTime.parse("07:00:00");
    public static LocalTime endTime1=LocalTime.parse("08:00:00");
    public static LocalTime startTime2=LocalTime.parse("17:00:00");
    public static LocalTime endTime2=LocalTime.parse("18:00:00");

    /**
     * @param Request
     * 改变系统时间
     *
     *
     */

    @RequestMapping("/TimeChange")
    public Results TimeChange(HttpServletRequest Request,@RequestBody Map<String, Object> request) {
        int id = 0;
        System.out.println("访问localhost:9000/AttendanceController/TimeChange !");
        try{
            String jwt = Request.getHeader("token");
            System.out.println("解析jwt="+jwt);
            Map<String, Object> claim =JwtUtils.ParseJwt(jwt);
            id = (int) claim.get("id");
            System.out.println("id :"+id);
        }catch (Exception e){

            return Results.Error("token过期，请重新登录！");
        }

        LocalTime startTime1 = LocalTime.parse((String)request.get("startTime1"));
        LocalTime startTime2 = LocalTime.parse((String)request.get("startTime2"));
        LocalTime endTime1=null;
        LocalTime endTime2=null;
        LocalTime duration=LocalTime.parse((String)request.get("duration"));
        if(duration!=null){
            AttendanceController.duration =duration;
        }

        if(startTime1 !=null){
            AttendanceController.startTime1 = startTime1;
            AttendanceController.endTime1 = addTime(startTime1,duration);
        }
        if(startTime2 != null){
            AttendanceController.startTime2 = startTime2;
            AttendanceController.endTime2 = addTime(startTime2,duration);
        }
        return Results.Success("修改成功！");
    }


    @Scheduled(cron = "0 0/1 * * * ?")
    public void scheduleMonitor() {
        System.out.println("------------");
        LocalTime now = LocalTime.now();

        if((now.isAfter(startTime1) && now.isBefore(startTime1.plus(1, ChronoUnit.MINUTES)) ||
                (now.isAfter(startTime2) && now.isBefore(startTime2.plus(1, ChronoUnit.MINUTES)))
        )){
            System.out.println("现在时间：" + now);
            System.out.println("执行添加");
            scheduleNewAttendance();

        }
        updateMeetingState();

        if((now.isAfter(endTime1) && now.isBefore(endTime1.plus(1, ChronoUnit.MINUTES))) ||
                (now.isAfter(endTime2) && now.isBefore(endTime2.plus(1, ChronoUnit.MINUTES)))
        ){
            System.out.println("现在时间：" + now);
            System.out.println("执行修改");
            scheduleUpdateAttendance();
        }






    }

    public  void updateMeetingState(){
        LocalTime now = LocalTime.now();
        List<Meeting> list=meetingService.meetingList();

        for(Meeting meeting:list){
            if(!meeting.getMeetingState().equals("已结束")){
                LocalDateTime localDateTime = meeting.getStartTime().toLocalDateTime();
                LocalTime startTime = localDateTime.toLocalTime();
                localDateTime = meeting.getEndTime().toLocalDateTime();
                LocalTime endTime = localDateTime.toLocalTime();
                if(now.isAfter(startTime)&& now.isBefore(endTime)){
                    meeting.setMeetingState("进行中");
                    meetingService.update(meeting);
                }else if (now.isAfter(endTime)){
                    meeting.setMeetingState("已结束");
                    meetingService.update(meeting);
                }

            }

        }

    }
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

    public void scheduleUpdateAttendance() {
        System.out.println("考勤截止！");
        Attendance atd = new Attendance();
        // 日期
        Date date= new Date(System.currentTimeMillis());
        atd.setDate(date);
        atd.setDeadline("否");
        List<Attendance> list= attendanceService.findAttendancesByAttendance(atd);

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

    public static LocalTime addTime(LocalTime start, LocalTime durationTime) {

        // 计算结束时间
        LocalTime endTime = start.plusHours(durationTime.getHour()).plusMinutes(durationTime.getMinute()).plusSeconds(durationTime.getSecond());

        // 如果结束时间超过24小时，则减去24小时
        if (endTime.isAfter(start.plusHours(24))) {
            endTime = endTime.minusHours(24);
        }

        // 返回结果时间
        return endTime;
    }
}
