package com.iworkcloud.service.impl;

import com.iworkcloud.mapper.AttendanceMapper;
import com.iworkcloud.mapper.BaseMapper;
import com.iworkcloud.pojo.Attendance;
import com.iworkcloud.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AttendanceServiceImpl extends BaseServiceImpl<Attendance> implements AttendanceService {

    @Autowired
    private AttendanceMapper attendanceMapper;

    @Override
    protected BaseMapper<Attendance> getMapper() {return this.attendanceMapper;}

    @Override
    public List<Attendance> findByUserId(Integer id) {
        return attendanceMapper.findByUserId(id);
    }
}
