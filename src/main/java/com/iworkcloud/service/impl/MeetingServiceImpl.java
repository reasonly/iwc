package com.iworkcloud.service.impl;

import com.iworkcloud.mapper.BaseMapper;
import com.iworkcloud.mapper.MeetingMapper;
import com.iworkcloud.pojo.Meeting;
import com.iworkcloud.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeetingServiceImpl extends BaseServiceImpl<Meeting> implements MeetingService {
    //mybatis
    @Autowired
    private MeetingMapper meetingMapper;

    @Override
    protected BaseMapper<Meeting> getMapper() {
        return this.meetingMapper;
    }

    @Override
    public Boolean findByAdministratorId(Meeting meeting) {
        try {
            System.out.println("findByAdministratorId");
            Meeting meeting1 = meetingMapper.findByAdministratorId(meeting);
            return meeting1 != null;
        } catch (Exception e) {
            System.out.println("查询失败!");
            e.printStackTrace();
        }
        return false;
    }

}
