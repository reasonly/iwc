package com.iworkcloud.mapper;

import com.iworkcloud.pojo.Meeting;
import com.iworkcloud.pojo.meetingAttendance;
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

    List<Meeting> findByuserId(int userId);


}
