package com.iworkcloud.mapper;

import com.iworkcloud.pojo.Finance;
import com.iworkcloud.pojo.FinanceManage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FinanceMapper extends BaseMapper<Finance>{
    boolean insertFinance(Finance finance);

    boolean insertFinanceManage(FinanceManage financeManage);

    boolean findFinanceByProjectId(Integer projectId);

    boolean updateProjectTotal(@Param("projectId") Integer projectId);

    Integer findFianceIdByFinance(Finance finance);

    boolean updateFinance(Finance finance);
}
