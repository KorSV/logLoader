package com.ksv.DAO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();
    public static Logger logger = LogManager.getLogger(HibernateUtil.class);

    public static SessionFactory buildSessionFactory(){
        try {
            return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        }catch (Throwable ex) {
            logger.error("Initial SessionFactory creation filed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }



}
