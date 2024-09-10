package com.iworkcloud.mapper;

import com.iworkcloud.pojo.Attendance;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AttendanceMapper extends BaseMapper<Attendance>{


    List<Attendance> findAllByUserId(Integer id);
    void attendanceByAttendanceId(Attendance attendance);
    Attendance findAttendanceByDateAndUserIdAndDeadline(Attendance atd);
    List<Attendance> findAttendancesByAttendance(Attendance atd);

    @Delete("DELETE FROM t_attendance WHERE date < NOW() - INTERVAL 3 MONTH")
    void deleteThreeMonthsBefore();
}
