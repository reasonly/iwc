package com.iworkcloud.mapper;

import com.iworkcloud.pojo.Finance;
import com.iworkcloud.pojo.FinanceManage;

import java.util.List;

public interface FinanceMapper extends BaseMapper<Finance>{
    boolean insertFinance(Finance finance);

    boolean insertFinanceManage(FinanceManage financeManage);

    boolean findFinanceByProjectId(Integer projectId);

    boolean updateProjectTotal(Integer projectId, Double amount);

    Integer findFianceIdByFinance(Finance finance);
}
