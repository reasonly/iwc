package com.iworkcloud.pojo;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Leave {
    private Integer leaveId;
    private Date startDate;
    private Date endDate;
    private String reason;
    private String state;
    private Integer userId;


}
