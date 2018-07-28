package com.solution.fromVC.dao;

import com.solution.fromVC.model.Assets;
import com.solution.fromVC.service.HibernateUtil;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Session;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

public class AssetDAOImp implements AssetDAO {

    private static final Session session = HibernateUtil.getSessionFactory().openSession();
//    openSession();

    @Override
    public void addNewAsset(Assets asset) {
        session.beginTransaction();
        session.persist(asset);
        session.getTransaction().commit();
    }

    @Override
    public void deleteAsset(Long id) {

    }

    @Override
    public void updateAsset(Assets asset) {

    }

    @Override
    public void removeAsset(List<Assets> asset) {
        session.beginTransaction();
        asset.forEach(session::remove);
        session.getTransaction().commit();
    }

    @Override
    public Assets findAsset(Long id) {
        return null;
    }

    @Override
    public Assets findAssetByNameAndDescription(String name, String description) {
        Query query = session.createNamedQuery("Asset.findByNameAnd Description", Assets.class)
                .setParameter("name", name)
                .setParameter("description", description);
        try{
            if(query.getSingleResult() != null){
                return (Assets) query.getSingleResult();
            }
        }catch (NoResultException | NonUniqueResultException | NullPointerException nur){
            return new Assets();
        }
        return (Assets) query.getSingleResult();
    }

    @Override
    public List<Assets> getAsset() {
        return session.createNamedQuery("Asset.getAsset", Assets.class).getResultList();
    }
}
