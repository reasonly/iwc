package com.iworkcloud.mapper;

import com.iworkcloud.pojo.Attendance;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceMapper extends BaseMapper<Attendance>{


    List<Attendance> findAllByUserId(Integer id);
    boolean attendanceByDateAndUserId(Attendance attendance);
}
