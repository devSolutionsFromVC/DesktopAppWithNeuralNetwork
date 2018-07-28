package com.solution.fromVC.service;

import com.solution.fromVC.model.Risk;

import java.util.List;

public interface RiskI {

    void addNewRisk(Risk risk);
    void deleteRisk(Long id);
    void updateRisk(Risk risk);
    void combinateRiskManager(Risk risk);
    void removeRisks(List<Risk> risks);
    Risk findRisk(Long id);
    Risk findByName(String name);
    List<Risk> getRisk();
}
