package com.iworkcloud.mapper;

import com.iworkcloud.pojo.Meeting;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 
* Title: UserDao
* Description:
* 用户数据接口 
* Version:1.0.0
 */
@Mapper
public interface MeetingMapper extends BaseMapper<Meeting> {

    List<Meeting> findByMeetingNum(int meetingNum);
    List<Meeting> findByAttendanceUser(int userId);
    List<Meeting> findByMeeting(Meeting meeting);
    boolean addAttendanceUser(Integer meetingId, List<Integer> userIdList);
    Integer findNum(int id);
    List<Integer> finduserId(int id);
    List<Meeting> findByMeetingId(int Id);
    Meeting updateState(Meeting meeting);
    void delete(int meetingId);



}
