package com.iworkcloud.service;

import com.iworkcloud.pojo.Attendance;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface AttendanceService extends BaseService<Attendance> {

    List<Attendance> findAllByUserId(Integer id);
    boolean attendanceByDateAndUserId(Attendance attendance);

}
