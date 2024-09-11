package com.iworkcloud.mapper;

import com.iworkcloud.pojo.Finance;
import com.iworkcloud.pojo.FinanceManage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FinanceMapper extends BaseMapper<Finance>{
    //插入财务信息
    boolean insertFinance(Finance finance);
    //插入财务管理信息
    boolean insertFinanceManage(FinanceManage financeManage);
    //根据项目ID查询财务信息
    boolean findFinanceByProjectId(Integer projectId);
    //更新项目总金额
    boolean updateProjectTotal(@Param("projectId") Integer projectId);
    //根据财务信息查找财务ID
    Integer findFianceIdByFinance(Finance finance);
    //更新财务信息
    boolean updateFinance(Finance finance);
}
