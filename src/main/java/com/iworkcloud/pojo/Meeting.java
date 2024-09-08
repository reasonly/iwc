package com.iworkcloud.pojo;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Meeting {
    private Integer meetingId;
    private Integer meetingNum;
    private String meetingName;
    private Timestamp startTime;
    private Timestamp endTime;
    private String meetingState;
    private Integer userId;


}
