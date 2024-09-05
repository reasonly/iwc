package com.iworkcloud.pojo.entity;

import java.util.Date;

public class Leave {
    protected Integer leaveId;
    private Date startrDate;
    private Date endDate;
    private String reason;
    private String status;
    private Integer userId;

    public Integer getLeaId() {
        return leaveId;
    }

    public void setLeaId(Integer leaId) {
        this.leaveId = leaId;
    }

    public Date getStartrDate() {
        return startrDate;
    }

    public void setStartrDate(Date startrDate) {
        this.startrDate = startrDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Leave{" +
                "leaId=" + leaveId +
                ", startrDate=" + startrDate +
                ", endDate=" + endDate +
                ", reason='" + reason + '\'' +
                ", status='" + status + '\'' +
                ", userId=" + userId +
                '}';
    }
}
