package com.iworkcloud.service;

import com.iworkcloud.pojo.Leave;

import java.util.List;

public interface LeaveService extends BaseService<Leave> {

    List<Leave> findAllByUserId(Integer id);
    Leave findLeaveByStartDateAndEndDate(Leave leave);
    Leave findLeaveByLeaveId(Integer id);

}
