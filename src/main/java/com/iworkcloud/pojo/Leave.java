package com.iworkcloud.pojo;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Leave {
    protected Integer leaveId;
    private Date startrDate;
    private Date endDate;
    private String reason;
    private String status;
    private Integer userId;


}
