package com.iworkcloud.service.impl;

import com.iworkcloud.mapper.AttendanceMapper;
import com.iworkcloud.mapper.BaseMapper;
import com.iworkcloud.pojo.Attendance;
import com.iworkcloud.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;


@Service
public class AttendanceServiceImpl extends BaseServiceImpl<Attendance> implements AttendanceService {

    @Autowired
    private AttendanceMapper attendanceMapper;

    @Override
    protected BaseMapper<Attendance> getMapper() {return this.attendanceMapper;}

    @Override
    public List<Attendance> findAllByUserId(Integer id) {
        return attendanceMapper.findAllByUserId(id);
    }

    @Override
    public boolean attendanceByAttendanceId(Attendance atd) {
        boolean flag = false;
        try{
            attendanceMapper.attendanceByAttendanceId(atd);
            flag=true;
        }catch(Exception e){
            System.out.println("attendance数据库修改失败");
        }

        return flag;
    }

    @Override
    public Attendance findAttendanceByDateAndUserId(Attendance atd) {
        return attendanceMapper.findAttendanceByDateAndUserId(atd);
    }

    @Override
    public List<Attendance> findAttendancesByAttendance(Attendance atd) {
        return attendanceMapper.findAttendancesByAttendance(atd);
    }
}
