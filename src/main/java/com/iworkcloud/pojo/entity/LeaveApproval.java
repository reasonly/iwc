package com.ruanko.pojo.entity;

public class LeaveApproval {
    protected Integer approvalId;
    private Integer leaveId;
    private Integer adminstratorId;

    public Integer getApprovalId() {
        return approvalId;
    }

    public void setApprovalId(Integer approvalId) {
        this.approvalId = approvalId;
    }

    public Integer getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(Integer leaveId) {
        this.leaveId = leaveId;
    }

    public Integer getAdminstratorId() {
        return adminstratorId;
    }

    public void setAdminstratorId(Integer adminstratorId) {
        this.adminstratorId = adminstratorId;
    }

    @Override
    public String toString() {
    	return "LeaveApproval [approvalId=" + approvalId + ", leaveId=" + leaveId + ", adminstratorId=" + adminstratorId + "]";
    }
}
