package com.iworkcloud.mapper;

import com.iworkcloud.pojo.entity.Attendance;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface AttendanceMapper extends BaseMapper<Attendance>{


    List<Attendance> findByUserId(Integer id);

}
