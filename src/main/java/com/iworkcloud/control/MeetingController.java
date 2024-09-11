package com.iworkcloud.control;

import com.iworkcloud.mapper.MeetingMapper;
import com.iworkcloud.pojo.Meeting;
import com.iworkcloud.pojo.Results;
import com.iworkcloud.service.MeetingService;
import com.iworkcloud.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;



@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/meeting")
public class MeetingController {

    @Autowired
    private MeetingService meetingService;
    @Autowired
    private MeetingMapper meetingMapper;

    @RequestMapping("/add")
    public Results addMeeting(HttpServletRequest Request, @RequestBody Map<String, Object> request)
    {
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
        Meeting meeting = new Meeting();
        meeting.setMeetingNum((Integer) request.get("meetingNum"));
        List<Meeting> num = meetingService.findByMeetingNum((Integer) request.get("meetingNum"));
        System.out.println("num="+num);
        meeting.setMeetingName((String) request.get("meetingName"));
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp localDate = Timestamp.valueOf(formatter.format(date));
        Timestamp startTime3 = Timestamp.valueOf((String) request.get("startTime"));
        Timestamp endTime3 = Timestamp.valueOf((String) request.get("endTime"));
        if (num.size() != 0){
            return Results.Error("编号重复！");
        }else if (startTime3.compareTo(localDate) < 0){
            return Results.Error("会议开始时间不能早于当前时间！");
        }else if (endTime3.compareTo(startTime3) < 0){
            return Results.Error("会议结束时间不能早于开始时间！");
        }else {
            meeting.setStartTime(startTime3);
            meeting.setEndTime(endTime3);
            meeting.setMeetingState("未开始");
            meeting.setUserId(id);
            meetingService.insert(meeting);
            return Results.Success();
        }
    }

    @RequestMapping("/delete")
    public Results deleteMeeting(HttpServletRequest Request,@RequestBody Map<String, Object> request)
    {
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
        int meetingId = (Integer) request.get("meetingId");
        List<Meeting> meetingList = meetingMapper.findByMeetingId(meetingId);
        if(meetingList.size() == 0){
            return Results.Error("不存在该会议");
        }else{
            meetingMapper.delete(meetingId);
            System.out.println("删除成功");
            return Results.Success();
        }

    }



    @RequestMapping("/edit")
    public Results editMeeting(HttpServletRequest Request,@RequestBody Map<String, Object> request)
    {
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
        Meeting meeting = new Meeting();
        int meetingId =(Integer) request.get("meetingId");
        meeting.setMeetingId(meetingId);
        meeting.setMeetingName((String) request.get("meetingName"));
        int number =(Integer) request.get("meetingNum");
        meeting.setMeetingNum(number);
        List<Meeting> num = meetingService.findByMeetingNum(number);
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp localDate = Timestamp.valueOf(formatter.format(date));
        Timestamp startTime3 = Timestamp.valueOf((String) request.get("startTime"));
        Timestamp endTime3 = Timestamp.valueOf((String) request.get("endTime"));
        if (num != null && meetingMapper.findNum(meetingId) != number){
            return Results.Error("编号重复！");
        }else if (startTime3.compareTo(localDate) < 0){
            return Results.Error("会议开始时间不能早于当前时间！");
        }else if (endTime3.compareTo(startTime3) < 0){
            return Results.Error("会议结束时间不能早于开始时间！");
        } else {
            meeting.setStartTime(startTime3);
            meeting.setEndTime(endTime3);
            meeting.setMeetingState("未开始");
            meetingService.update(meeting);
            return Results.Success();
        }
    }

    @RequestMapping("/search")
    public Results search(HttpServletRequest Request,@RequestBody Map<String, Object> request){
        int id = 0;
        String authority =null;
        try{
            String jwt = Request.getHeader("token");
            System.out.println("解析jwt="+jwt);
            Map<String, Object> claim =JwtUtils.ParseJwt(jwt);
            id = (int) claim.get("id");
            authority =(String) claim.get("authority");
            System.out.println("解析令牌得id="+id+" authority="+authority);
        }catch (Exception e){
            return Results.Error("token过期，请重新登录！");
        }

        Meeting meeting = new Meeting();
        meeting.setMeetingNum((Integer) request.get("meetingNum"));
        meeting.setMeetingName((String) request.get("meetingName"));
        meeting.setMeetingState((String) request.get("meetingState"));
        if(authority == "员工"){
            meeting.setUserId(id);
        }
        List<Meeting> meetingList = meetingMapper.findByMeeting(meeting);
        if (meetingList.size() == 0){
            return Results.Error("没有该会议");
        }else{
            Map<String, Object> claims = new HashMap<>();
            claims.put("meetingList", meetingList);
            return Results.Success(meetingList);
        }

    }

    @RequestMapping("/addAttendanceUser")
    public Results addAttendanceUser(HttpServletRequest Request,@RequestBody Map<String, Object> request)
    {
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
        Integer meetingtId = (Integer) request.get("meetingId");
        List<Meeting> meetingList = meetingMapper.findByMeetingId(meetingtId);
        if (meetingList.size() == 0)
        {
            return Results.Error("该会议不存在");
        }else {
            List<Integer> userIdList = (List<Integer>) request.get("userIdList");
            List<Integer> existUserId = meetingMapper.finduserId(meetingtId);
            System.out.println(existUserId);
            for (int i = 0; i < userIdList.size(); i++){
                for(int j = 0; j < userIdList.size(); j++){
                    if (existUserId.get(i) == userIdList.get(j)){
                        System.out.println("删除重复元素");
                        userIdList.remove(j);
                    }
                }
            }
            if(meetingMapper.addAttendanceUser(meetingtId,userIdList)) {
                return Results.Success("成功添加员工到该会议");
            }
            return Results.Error("添加失败");
        }

    }
    //员工看到的会议列表
    @RequestMapping("/meetinglistByAttendance")
    public Results meetingListByUserId(HttpServletRequest Request,@RequestBody Map<String, Object> request)
    {
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
        List<Meeting> meetingList = meetingService.meetingListByUserId((Integer) request.get("userId"));

        if (meetingList.size() == 0){
            return Results.Error("该用户没有会议！");
        }else {
            Map<String, Object> claims = new HashMap<>();
            claims.put("meetingList", meetingList);
            return Results.Success(meetingList);
        }

    }
    //管理员的看到的会议列表
    @RequestMapping("/meetinglist")
    public Results meetingList(HttpServletRequest Request)
    {
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
        List<Meeting> meetingList = meetingService.meetingList();
        Map<String, Object> claims = new HashMap<>();
        claims.put("meetingList", meetingList);
        return Results.Success(meetingList);
    }


}
