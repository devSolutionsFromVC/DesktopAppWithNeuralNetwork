package com.solution.fromVC.dao;

import com.solution.fromVC.model.RiskManager;

public interface RiskManagerDAO {

    void addNewManager(RiskManager riskManager);
    void deleteManager(Long id);
    void updateManager(RiskManager riskManager);
    RiskManager findManager(Long id);
    RiskManager findManagerByUsername(String username);

}
