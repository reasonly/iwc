package com.iworkcloud.pojo.entity;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Timestamp;

public class FinanceManage {
    private Integer financeManageId;
    private Integer financeId;
    private Integer administratorId;
    private Timestamp financeManageTime;
    private String financeManageDescription;

    public Integer getFinanceManageId() {
        return financeManageId;
    }

    public void setFinanceManageId(Integer financeManageId) {
        this.financeManageId = financeManageId;
    }

    public Integer getFinanceId() {
        return financeId;
    }

    public void setFinanceId(Integer financeId) {
        this.financeId = financeId;
    }

    public Integer getAdministratorId() {
        return administratorId;
    }

    public void setAdministratorId(Integer administratorId) {
        this.administratorId = administratorId;
    }

    public Timestamp getFinanceManageTime() {
        return financeManageTime;
    }

    public void setFinanceManageTime(Timestamp financeManageTime) {
        this.financeManageTime = financeManageTime;
    }

    public String getFinanceManageDescription() {
        return financeManageDescription;
    }

    public void setFinanceManageDescription(String financeManageDescription) {
        this.financeManageDescription = financeManageDescription;
    }

    @Override
    public String toString() {
        return "FinanceManage{" +
                "financeManageId=" + financeManageId +
                ", financeId=" + financeId +
                ", administratorId=" + administratorId +
                ", financeManageTime=" + financeManageTime +
                ", financeManageDescription='" + financeManageDescription + '\'' +
                '}';
    }
}
