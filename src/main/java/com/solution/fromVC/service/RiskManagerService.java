package com.solution.fromVC.service;

import com.solution.fromVC.model.RiskManager;

public interface RiskManagerService {

    void addNewManager(RiskManager riskManager);
    void deleteManager(Long id);
    void updateManager(RiskManager riskManager);
    RiskManager findManager(Long id);
    RiskManager findManagerByUsername(String username);
}
