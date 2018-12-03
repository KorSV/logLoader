package com.ksv.DAO;

import com.ksv.Log;
import com.ksv.LogRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class DAOLogRecord {
    private static Logger logger = LogManager.getLogger(DAOLogRecord.class);

    public void saveAll(Log log){
        Session session = HibernateUtil.buildSessionFactory().openSession();
        session.beginTransaction();
        for (LogRecord logRecord : log.getLines()) {
            try {
                session.save(logRecord);
            } catch (PersistenceException e){
                logger.error("Ошибка записи в бд!!! Объект"+logRecord);
                logger.error(e);
            }
        }
        session.getTransaction().commit();
        HibernateUtil.shutdown();
    }

    public LogRecord getLastRecord(){
        Session session = HibernateUtil.buildSessionFactory().openSession();
        session.beginTransaction();

        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<Integer> criteriaQuerymaxId = builder.createQuery(Integer.class);
            Root<LogRecord> rootMaxId = criteriaQuerymaxId.from(LogRecord.class);
            criteriaQuerymaxId.select(builder.max(rootMaxId.<Integer>get("logId")));
            Query<Integer> queryMaxId = session.createQuery(criteriaQuerymaxId);
            int maxId = queryMaxId.getSingleResult();
            CriteriaQuery<LogRecord> query = builder.createQuery(LogRecord.class);
            Root<LogRecord> root = query.from(LogRecord.class);
            query.select(root).where(builder.equal(root.get("logId"),maxId));
            Query<LogRecord> q = session.createQuery(query);



            LogRecord lastLogRecord = q.getSingleResult();
            session.getTransaction().commit();

            HibernateUtil.shutdown();
            return lastLogRecord;
        }catch (Exception e){
            return null;
        }

    }
}
