package com.iworkcloud.mapper;

import com.iworkcloud.pojo.Leave;

import java.util.List;

public interface LeaveMapper extends BaseMapper<Leave>{
    List<Leave> findAllByUserId(Integer id);
    Leave findLeaveByStartDateAndEndDate(Leave leave);
    Leave findLeaveByLeaveId(Integer id);
}
