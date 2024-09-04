package com.iworkcloud.pojo.entity;

public class ProjectAttendance {

    private Integer joinId;
    private Integer projectId;
    private Integer userId;

    @Override
    public String toString() {
        return "ProjectAttendance{" +
                "joinId=" + joinId +
                ", projectId=" + projectId +
                ", userId=" + userId +
                '}';
    }

    public Integer getJoinId() {
        return joinId;
    }

    public void setJoinId(Integer joinId) {
        this.joinId = joinId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
