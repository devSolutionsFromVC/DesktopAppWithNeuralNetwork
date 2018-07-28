package com.solution.fromVC.service;

import com.solution.fromVC.dao.RiskDAO;
import com.solution.fromVC.dao.RiskDAOImp;
import com.solution.fromVC.model.Risk;

import java.util.List;

public class RiskServiceImp implements RiskI {

    private RiskDAO riskDAO;

    public RiskServiceImp() {
        riskDAO = new RiskDAOImp();
    }

    @Override
    public void addNewRisk(Risk risk) {
        riskDAO.addNewRisk(risk);
    }

    @Override
    public void deleteRisk(Long id) {
        riskDAO.deleteRisk(id);
    }

    @Override
    public void updateRisk(Risk risk) {
        riskDAO.updateRisk(risk);
    }

    @Override
    public void combinateRiskManager(Risk risk) {
        riskDAO.combinateRiskManager(risk);
    }

    @Override
    public void removeRisks(List<Risk> risks) {
        riskDAO.removeRisks(risks);
    }

    @Override
    public Risk findRisk(Long id) {
        return riskDAO.findRisk(id);
    }

    @Override
    public Risk findByName(String name) {
        return riskDAO.findByName(name);
    }

    @Override
    public List<Risk> getRisk() {
        return riskDAO.getRisk();
    }
}
