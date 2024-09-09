package com.iworkcloud.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveApproval {
    private Integer approvalId;
    private Integer leaveId;
    private Integer userId;


}
