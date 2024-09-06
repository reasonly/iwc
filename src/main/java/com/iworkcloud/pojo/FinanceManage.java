package com.iworkcloud.pojo;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinanceManage {
    private Integer financeManageId;
    private Integer financeId;
    private Integer administratorId;
    private Timestamp financeManageTime;
    private String financeManageDescription;


}
