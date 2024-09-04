package com.iworkcloud.pojo.entity;

import java.sql.Timestamp;

public class UserManage {

    private Integer manageId;
    private Integer userId;
    private Integer administratorId;
    private String manageDescription;
    private Timestamp manageTime;

    @Override
    public String toString() {
        return "UserManage{" +
                "manageId=" + manageId +
                ", userId=" + userId +
                ", administratorId=" + administratorId +
                ", manageDescription='" + manageDescription + '\'' +
                ", manageTime='" + manageTime + '\'' +
                '}';
    }

    public Integer getManageId() {
        return manageId;
    }

    public void setManageId(Integer manageId) {
        this.manageId = manageId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAdministratorId() {
        return administratorId;
    }

    public void setAdministratorId(Integer administratorId) {
        this.administratorId = administratorId;
    }

    public String getManageDescription() {
        return manageDescription;
    }

    public void setManageDescription(String manageDescription) {
        this.manageDescription = manageDescription;
    }

    public Timestamp getManageTime() {
        return manageTime;
    }

    public void setManageTime(Timestamp manageTime) {
        this.manageTime = manageTime;
    }
}
