package com.ksv.printers;


import com.ksv.DAO.*;
import com.ksv.Log;
import org.hibernate.Session;

public class DbPrinter extends Printer implements IPrinter {

    public DbPrinter(Log log, Session session) {
        super(log, session);
    }

    public void print(Log log){
        DAOLogRecord dao = new DAOLogRecord(session);
        dao.saveAll(log);
        logger.info("В базу данных загружено "+ log.size() +" записей.");
    }
}
