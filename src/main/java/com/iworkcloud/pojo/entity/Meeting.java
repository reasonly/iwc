package com.iworkcloud.pojo.entity;

import java.sql.Timestamp;

public class Meeting {
    protected Integer meetingId;
    private Integer meetingNum;
    private String meetingName;
    private Timestamp startTime;
    private Timestamp endTime;
    private String meetingState;
    private Integer administratorId;

    public Integer getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(Integer meetingId) {
        this.meetingId = meetingId;
    }

    public Integer getMeetingNum() {
        return meetingNum;
    }

    public void setMeetingNum(Integer meetingNum) {
        this.meetingNum = meetingNum;
    }

    public String getMeetingName() {
        return meetingName;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public String getMeetingState() {
        return meetingState;
    }

    public void setMeetingState(String meetingState) {
        this.meetingState = meetingState;
    }

    public Integer getAdministratorId() {
        return administratorId;
    }

    public void setAdministratorId(Integer administratorId) {
        this.administratorId = administratorId;
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "meetingId=" + meetingId +
                ", meetingNum=" + meetingNum +
                ", meetingName='" + meetingName + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", meetingState='" + meetingState + '\'' +
                ", administratorId=" + administratorId +
                '}';
    }
}
