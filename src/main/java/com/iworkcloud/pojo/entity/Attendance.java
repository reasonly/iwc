package com.iworkcloud.pojo.entity;

import com.iworkcloud.TUser;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;


public class Attendance {

    private Integer attendcanceId;

    private Integer userId;

    private Integer attendanceState;

    private Timestamp attendanceTime;


    private Date date;

    public Integer getAttendcanceId() {
        return attendcanceId;
    }

    public void setAttendcanceId(Integer attendcanceId) {
        this.attendcanceId = attendcanceId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAttendanceState() {
        return attendanceState;
    }

    public void setAttendanceState(Integer attendanceState) {
        this.attendanceState = attendanceState;
    }

    public Timestamp getAttendanceTime() {
        return attendanceTime;
    }

    public void setAttendanceTime(Timestamp attendanceTime) {
        this.attendanceTime = attendanceTime;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "attendcanceId=" + attendcanceId +
                ", userId=" + userId +
                ", attendanceState=" + attendanceState +
                ", attendanceTime=" + attendanceTime +
                ", date=" + date +
                '}';
    }
}