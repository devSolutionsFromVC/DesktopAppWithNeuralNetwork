package com.solution.fromVC.dao;

import com.solution.fromVC.model.Risk;
import com.solution.fromVC.service.HibernateUtil;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Session;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

public class RiskDAOImp implements RiskDAO{

    private static final Session session = HibernateUtil.getSessionFactory().openSession();

    @Override
    public void addNewRisk(Risk risk) {
        session.beginTransaction();
        session.persist(risk);
        session.getTransaction().commit();
    }

    @Override
    public void deleteRisk(Long id) {
        session.delete(id);
    }

    @Override
    public void updateRisk(Risk risk) {
        session.merge(risk);
    }

    @Override
    public void combinateRiskManager(Risk risk) {
        session.beginTransaction();
        session.merge(risk);
        session.getTransaction().commit();
    }

    @Override
    public void removeRisks(List<Risk> risks) {
        session.beginTransaction();
        risks.forEach(session::remove);
        session.getTransaction().commit();
    }

    @Override
    public Risk findRisk(Long id) {
        return session.find(Risk.class, id);
    }

    @Override
    public Risk findByName(String name) {
        Query query = session.createNamedQuery("Risk.findByName", Risk.class)
                .setParameter("name", name);
        try{
            if(query.getSingleResult() != null){
                return (Risk) query.getSingleResult();
            }
        }catch (NoResultException | NonUniqueResultException nur){
            return new Risk();
        }
        return (Risk) query.getSingleResult();
    }

    @Override
    public List<Risk> getRisk() {
        return session.createNamedQuery("Risk.getRisk", Risk.class).getResultList();
    }
}
