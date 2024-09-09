package com.iworkcloud.mapper;

import com.iworkcloud.pojo.Attendance;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface AttendanceMapper extends BaseMapper<Attendance>{


    List<Attendance> findAllByUserId(Integer id);
    void attendanceByAttendanceId(Attendance attendance);
    Attendance findAttendanceByDateAndUserId(Attendance atd);
    List<Attendance> findAttendancesByAttendance(Attendance atd);
}
