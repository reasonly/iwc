package com.iworkcloud.mapper;

import com.iworkcloud.pojo.Leave;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LeaveMapper extends BaseMapper<Leave>{
    List<Leave> findAllByUserId(Integer id);
    Leave findLeaveByStartDateAndEndDate(Leave leave);
    Leave findLeaveByLeaveId(Integer id);

}
