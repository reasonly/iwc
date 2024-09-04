package com.ruanko.pojo.entity;

public class meetingAttendance {
    protected Integer meetingAttendanceId;
    private Integer meetingId;
    private Integer userId;

    public Integer getMeetingAttendanceId() {
        return meetingAttendanceId;
    }

    public void setMeetingAttendanceId(Integer meetingAttendanceId) {
        this.meetingAttendanceId = meetingAttendanceId;
    }

    public Integer getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(Integer meetingId) {
        this.meetingId = meetingId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "meetingAttendance{" +
                "meetingAttendanceId=" + meetingAttendanceId +
                ", meetingId=" + meetingId +
                ", userId=" + userId +
                '}';
    }
}
