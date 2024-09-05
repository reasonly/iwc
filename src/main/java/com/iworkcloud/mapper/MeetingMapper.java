package com.iworkcloud.mapper;

import com.iworkcloud.pojo.entity.Meeting;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
* Title: UserDao
* Description:
* 用户数据接口 
* Version:1.0.0
 */
@Mapper
public interface MeetingMapper extends BaseMapper<Meeting> {

    Meeting findByAdministratorId(Meeting meeting);


}
