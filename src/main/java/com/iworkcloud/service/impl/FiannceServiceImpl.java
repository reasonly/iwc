package com.iworkcloud.service.impl;

import com.iworkcloud.mapper.BaseMapper;
import com.iworkcloud.mapper.FinanceMapper;
import com.iworkcloud.pojo.Finance;
import com.iworkcloud.pojo.FinanceManage;
import com.iworkcloud.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FiannceServiceImpl extends BaseServiceImpl<Finance> implements FinanceService {
    @Autowired
    private FinanceMapper financeMapper;
    @Override
    protected BaseMapper<Finance> getMapper() {
        return this.financeMapper;
    }

    @Override
    public List<Finance> financeList() {
        return financeMapper.findAll();
    }

    @Override
    public List<Finance> financeList(Finance finance) {
        return financeMapper.findByListEntity(finance);
    }
    @Override
    public boolean addFinance(Finance finance) {
        return financeMapper.insertFinance(finance);

    }

    @Override
    public boolean addFinanceManage(FinanceManage financeManage) {
        return financeMapper.insertFinanceManage(financeManage);
    }
    public Finance findByFinance(Finance finance){
        return financeMapper.findByEntity(finance);
    }
    @Override
    public boolean findFinanceByProjectId(Integer projectId){
        return financeMapper.findFinanceByProjectId(projectId);
    }
    @Override
    public boolean updateProjectTotal(Integer projectId, Double amount){
        return financeMapper.updateProjectTotal(projectId,amount);
    }
    public Integer findFianceIdByFinance(Finance finance){
        return financeMapper.findFianceIdByFinance(finance);
    }
}
