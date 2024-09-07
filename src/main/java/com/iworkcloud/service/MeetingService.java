package com.iworkcloud.service;

import com.iworkcloud.pojo.Meeting;

import java.util.List;


/**
 * 
* Title: UserService
* Description: 
* 用户接口 
* Version:1.0.0  

 */
public interface MeetingService extends BaseService<Meeting>{

    List<Meeting> meetingList();

    List<Meeting> meetingList(Integer id);
}
