package com.iworkcloud.service;

import com.iworkcloud.pojo.entity.Attendance;

import java.util.List;

public interface AttendanceService extends BaseService<Attendance> {

    List<Attendance> findByUserId(Integer id);
}
