package com.iworkcloud.service;

import com.iworkcloud.pojo.entity.Meeting;


/**
 * 
* Title: UserService
* Description: 
* 用户接口 
* Version:1.0.0  

 */
public interface MeetingService extends BaseService<Meeting>{


    Boolean findByAdministratorId(Meeting meeting);

}
