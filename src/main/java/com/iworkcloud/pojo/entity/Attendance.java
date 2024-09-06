package com.iworkcloud.pojo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;


public class Attendance {

    private Integer attendanceId;

    private Integer userId;

    private String attendanceState;

    private Timestamp attendanceTime;


    private Date date;

    @Override
    public String toString() {
        return "Attendance{" +
                "attendcanceId=" + attendanceId +
                ", userId=" + userId +
                ", attendanceState=" + attendanceState +
                ", attendanceTime=" + attendanceTime +
                ", date=" + date +
                '}';
    }

    public Integer getAttendcanceId() {
        return attendanceId;
    }

    public void setAttendcanceId(Integer attendcanceId) {
        this.attendanceId = attendcanceId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Timestamp getAttendanceTime() {
        return attendanceTime;
    }

    public void setAttendanceTime(Timestamp attendanceTime) {
        this.attendanceTime = attendanceTime;
    }

    public String getAttendanceState() {
        return attendanceState;
    }

    public void setAttendanceState(String attendanceState) {
        this.attendanceState = attendanceState;
    }

    public Integer getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(Integer attendanceId) {
        this.attendanceId = attendanceId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}