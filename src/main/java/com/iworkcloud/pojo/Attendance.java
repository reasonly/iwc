package com.iworkcloud.pojo;

import java.sql.Date;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Attendance {

    private Integer attendanceId;

    private Integer userId;

    private String attendanceState;

    private Timestamp attendanceTime;

    private Date date;


}