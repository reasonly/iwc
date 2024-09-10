package com.iworkcloud.service.impl;

import com.iworkcloud.mapper.BaseMapper;
import com.iworkcloud.mapper.MeetingMapper;
import com.iworkcloud.pojo.Meeting;
import com.iworkcloud.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Meeting> meetingList() { return meetingMapper.findAll(); }
    @Override
    public List<Meeting> meetingListByUserId(Integer userid){

        return meetingMapper.findByAttendanceUser(userid);
    }

}
