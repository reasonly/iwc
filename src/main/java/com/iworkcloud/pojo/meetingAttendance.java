package com.iworkcloud.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class meetingAttendance {
    protected Integer meetingAttendanceId;
    private Integer meetingId;
    private Integer userId;


}
