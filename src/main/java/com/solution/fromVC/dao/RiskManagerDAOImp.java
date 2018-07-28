package com.solution.fromVC.dao;

import com.solution.fromVC.model.RiskManager;
import com.solution.fromVC.service.HibernateUtil;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Session;

import javax.persistence.NoResultException;
import javax.persistence.Query;

public class RiskManagerDAOImp implements RiskManagerDAO{

    private static final Session session = HibernateUtil.getSessionFactory().openSession();

    @Override
    public void addNewManager(RiskManager riskManager) {
        session.persist(riskManager);
    }

    @Override
    public void deleteManager(Long id) {
        RiskManager riskManager = session.find(RiskManager.class, id);

        if(riskManager != null){
            session.delete(riskManager);
        }
    }

    @Override
    public void updateManager(RiskManager riskManager) {
        session.update(riskManager);
    }

    @Override
    public RiskManager findManager(Long id) {
        return session.find(RiskManager.class, id);
    }

    @Override
    public RiskManager findManagerByUsername(String username) {
        Query query = session.createNamedQuery("Manager.findByName", RiskManager.class)
                .setParameter("name", username);
        try {
            if (query.getSingleResult() != null) {
                return (RiskManager) query.getSingleResult();
            }
        }catch (NoResultException | NonUniqueResultException nre){
            return new RiskManager();
        }
        return (RiskManager) query.getSingleResult();
    }

}
