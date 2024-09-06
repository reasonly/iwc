package com.iworkcloud.pojo;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserManage {

    private Integer manageId;
    private Integer userId;
    private Integer administratorId;
    private String manageDescription;
    private Timestamp manageTime;


}
