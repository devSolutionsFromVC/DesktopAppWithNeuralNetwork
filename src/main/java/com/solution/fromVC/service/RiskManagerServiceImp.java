package com.solution.fromVC.service;

import com.solution.fromVC.dao.RiskManagerDAO;
import com.solution.fromVC.dao.RiskManagerDAOImp;
import com.solution.fromVC.model.RiskManager;

public class RiskManagerServiceImp implements RiskManagerService {


    private RiskManagerDAO riskManagerDAO;

    public RiskManagerServiceImp(RiskManagerDAO riskManagerDAO) {
        this.riskManagerDAO = riskManagerDAO;
    }

    public RiskManagerServiceImp() {
        riskManagerDAO = new RiskManagerDAOImp();
    }

    @Override
    public void addNewManager(RiskManager riskManager) {
        riskManagerDAO.addNewManager(riskManager);
    }

    @Override
    public void deleteManager(Long id) {
        riskManagerDAO.deleteManager(id);
    }

    @Override
    public void updateManager(RiskManager riskManager) {
        riskManagerDAO.updateManager(riskManager);
    }



    @Override
    public RiskManager findManager(Long id) {
        return riskManagerDAO.findManager(id);
    }

    @Override
    public RiskManager findManagerByUsername(String username) {
        return riskManagerDAO.findManagerByUsername(username);
    }

}
