package com.iworkcloud.pojo;


import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Finance {
    private Integer financeId;
    private String financeType;
    private Double amount;
    private String financeDescription;
    private Timestamp financeRecordTime;
    private Integer userId;
    private Integer projectId;


}
