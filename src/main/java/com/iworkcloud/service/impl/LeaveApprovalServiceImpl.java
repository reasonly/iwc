package com.iworkcloud.service.impl;

import com.iworkcloud.mapper.BaseMapper;
import com.iworkcloud.mapper.LeaveApprovalMapper;
import com.iworkcloud.pojo.LeaveApproval;

import com.iworkcloud.service.LeaveApprovalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeaveApprovalServiceImpl extends BaseServiceImpl<LeaveApproval> implements LeaveApprovalService {

    @Autowired
    private LeaveApprovalMapper leaveApprovalMapper;
    @Override
    protected BaseMapper<LeaveApproval> getMapper() {
        return this.leaveApprovalMapper;
    }
}
