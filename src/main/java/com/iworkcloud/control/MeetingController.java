package com.iworkcloud.control;

import com.iworkcloud.pojo.Meeting;
import com.iworkcloud.service.MeetingService;
import com.iworkcloud.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/meeting")
public class MeetingController {

    @Autowired
    private MeetingService meetingService;

    @RequestMapping("/add")
    public void addMeeting(HttpServletRequest Request, @RequestBody Map<String, Object> request)
    {
        Meeting meeting = new Meeting();
        meeting.setMeetingNum((Integer) request.get("meetingNum"));
        meeting.setMeetingName((String) request.get("meetingName"));
        meeting.setMeetingState((String) request.get("meetingState"));
        meeting.setStartTime((Timestamp) request.get("startTime"));
        meeting.setEndTime((Timestamp) request.get("endTime"));
        String jwt = Request.getHeader("token");
        Map<String, Object> claim = JwtUtils.ParseJwt(jwt);
        int id = (int) claim.get("id");
        meeting.setUserId(id);
        meetingService.insert(meeting);
    }

    @RequestMapping("/delete")
    public void deleteMeeting(@RequestBody Map<String, Object> request)
    {
        meetingService.deleteByPrimaryKey((Integer) request.get("id"));
        System.out.println("删除成功");
    }

    @RequestMapping("/edit")
    public void editMeeting(@RequestBody Map<String, Object> request)
    {
        Meeting meeting = new Meeting();
        meeting.setMeetingId((Integer) request.get("meetingId"));
        meeting.setMeetingName((String) request.get("meetingName"));
        meeting.setMeetingNum((Integer) request.get("meetingNum"));
        meeting.setMeetingState((String) request.get("meetingState"));
        meeting.setStartTime((Timestamp) request.get("startTime"));
        meetingService.update(meeting);
    }
    //员工看到的会议列表
    @RequestMapping("/meetinglistByUserId")
    public List<Meeting> meetingListByUserId(HttpServletRequest Request)
    {
        String jwt = Request.getHeader("token");
        Map<String, Object> claim = JwtUtils.ParseJwt(jwt);
        int id = (int) claim.get("id");
        return meetingService.meetingList(id);
    }
    //管理员的看到的会议列表
    @RequestMapping("/meetinglist")
    public List<Meeting> meetingList(HttpServletRequest Request)
    {
        return meetingService.meetingList();
    }

}
