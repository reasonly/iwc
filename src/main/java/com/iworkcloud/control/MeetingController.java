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

import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;



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
        meeting.setMeetingNum(Integer.parseInt((String) request.get("meetingNum")));
        meeting.setMeetingName((String) request.get("meetingName"));
        Date date = new Date();
        Timestamp localDate = Timestamp.valueOf(formatter.format(date));
        Timestamp startTime = Timestamp.valueOf((String) request.get("startTime"));
        Timestamp endTime = Timestamp.valueOf((String) request.get("endTime"));
        meeting.setStartTime(startTime);
        meeting.setEndTime(endTime);
        //meeting.setMeetingState();
        meeting.setUserId(id);
        meetingService.insert(meeting);
        return Results.Success();
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
        meetingMapper.delete(Integer.parseInt((String) request.get("meetingId")));
        System.out.println("删除成功");
        return Results.Success();
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
        meeting.setMeetingId(Integer.parseInt((String) request.get("meetingId")));
        meeting.setMeetingName((String) request.get("meetingName"));
        meeting.setMeetingNum(Integer.parseInt((String) request.get("meetingNum")));
        meeting.setMeetingState((String) request.get("meetingState"));
        meeting.setStartTime(Timestamp.valueOf((String) request.get("startTime")));
        meeting.setEndTime(Timestamp.valueOf((String) request.get("endTime")));
        meetingService.update(meeting);
        return Results.Success();
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

        return Results.Success(meetingService.meetingListByUserId(Integer.parseInt((String) request.get("userId"))));
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

        return Results.Success(meetingService.meetingList());
    }

}
