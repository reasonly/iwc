package com.iworkcloud.mapper;

import com.iworkcloud.pojo.Attendance;

import java.util.List;

public interface AttendanceMapper extends BaseMapper<Attendance>{


    List<Attendance> findByUserId(Integer id);

}
