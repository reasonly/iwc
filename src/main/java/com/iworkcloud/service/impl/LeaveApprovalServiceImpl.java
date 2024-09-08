package com.iworkcloud.service.impl;

import com.iworkcloud.mapper.BaseMapper;
import com.iworkcloud.pojo.LeaveApproval;

import com.iworkcloud.service.LeaveApprovalService;

import org.springframework.stereotype.Service;

@Service
public class LeaveApprovalServiceImpl extends BaseServiceImpl<LeaveApproval> implements LeaveApprovalService {

    @Override
    protected BaseMapper<LeaveApproval> getMapper() {
        return null;
    }
}
