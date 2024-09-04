package com.iworkcloud.pojo.entity;

import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.sql.Date;
import java.sql.Timestamp;

public class Finance {
    private Integer financeId;
    private String financeType;
    private float amount;
    private String financeDescription;
    private Timestamp financeRecordTime;
    private Integer userId;
    private Integer projectId;

    public Integer getFinanceId() {
        return financeId;
    }

    public void setFinanceId(Integer financeId) {
        this.financeId = financeId;
    }

    public String getFinanceType() {
        return financeType;
    }

    public void setFinanceType(String financeType) {
        this.financeType = financeType;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getFinanceDescription() {
        return financeDescription;
    }

    public void setFinanceDescription(String financeDescription) {
        this.financeDescription = financeDescription;
    }

    public Timestamp getFinanceRecordTime() {
        return financeRecordTime;
    }

    public void setFinanceRecordTime(Timestamp financeRecordTime) {
        this.financeRecordTime = financeRecordTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "Finance{" +
                "financeId=" + financeId +
                ", financeType='" + financeType + '\'' +
                ", amount=" + amount +
                ", financeDescription='" + financeDescription + '\'' +
                ", financeRecordTime=" + financeRecordTime +
                ", userId=" + userId +
                ", projectId=" + projectId +
                '}';
    }
}
