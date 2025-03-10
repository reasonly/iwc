package com.iworkcloud.service;

import com.iworkcloud.pojo.Attendance;

import java.util.List;

public interface AttendanceService extends BaseService<Attendance> {

    List<Attendance> findAllByUserId(Integer id);
    boolean attendanceByAttendanceId(Attendance attendance);
    Attendance findAttendanceByDateAndUserIdAndDeadline(Attendance atd);
    List<Attendance> findAttendancesByAttendance(Attendance atd);
    void deleteThreeMonthsBefore();

}
