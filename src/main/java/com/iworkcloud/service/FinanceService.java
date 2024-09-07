package com.iworkcloud.service;

import com.iworkcloud.pojo.Finance;
import com.iworkcloud.pojo.FinanceManage;

import java.util.List;

public interface FinanceService extends BaseService<Finance>{
    List<Finance> financeList();
    List<Finance> financeList(Finance finance);
    boolean addFinance(Finance finance);

    boolean addFinanceManage(FinanceManage financeManage);

    Finance findByFinance(Finance finance);

    boolean findFinanceByProjectId(Integer projectId);

    boolean updateProjectTotal(Integer projectId, Double amount);

    Integer findFianceIdByFinance(Finance finance);
}
