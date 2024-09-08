package com.iworkcloud.service.impl;

import com.iworkcloud.mapper.AttendanceMapper;
import com.iworkcloud.mapper.BaseMapper;
import com.iworkcloud.mapper.LeaveMapper;
import com.iworkcloud.pojo.Attendance;
import com.iworkcloud.pojo.Leave;
import com.iworkcloud.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class LeavaServiceImpl extends BaseServiceImpl<Leave> implements LeaveService {
    @Autowired
    private LeaveMapper leaveMapper;

    @Override
    protected BaseMapper<Leave> getMapper() {return this.leaveMapper;}

    @Override
    public List<Leave> findAllByUserId(Integer id) {
        return leaveMapper.findAllByUserId(id);
    }

    @Override
    public Leave findLeaveByStartDateAndEndDate(Leave leave) {
        return leaveMapper.findLeaveByStartDateAndEndDate(leave);
    }

    @Override
    public Leave findLeaveByLeaveId(Integer id) {
        return leaveMapper.findLeaveByLeaveId(id);
    }


}
