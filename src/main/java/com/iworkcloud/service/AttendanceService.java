package com.iworkcloud.service;

import com.iworkcloud.pojo.Attendance;

import java.util.List;

public interface AttendanceService extends BaseService<Attendance> {

    List<Attendance> findAllByUserId(Integer id);
    boolean attendanceByAttendanceId(Attendance attendance);
    Attendance findAttendanceByDateAndUserId(Attendance atd);

}
