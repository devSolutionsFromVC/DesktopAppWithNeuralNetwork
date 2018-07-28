package com.solution.fromVC.service;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try{
            Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
            sessionFactory = configuration.configure().buildSessionFactory();
            return sessionFactory;
        }catch (Exception ex){
            System.err.println("Exception of initializer");
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

    public static void shutdown(){
        getSessionFactory().close();
    }
}
