package com.ruanko.pojo.entity;

import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.sql.Timestamp;
import java.util.Date;

public class Meeting {
    protected Integer meetingId;
    private Integer meetingNum;
    private String meetingName;
    private Timestamp startDate;
    private Timestamp endDate;
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

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
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
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", meetingState='" + meetingState + '\'' +
                ", administratorId=" + administratorId +
                '}';
    }
}
