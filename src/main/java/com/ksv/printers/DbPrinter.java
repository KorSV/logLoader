package com.ksv.printers;


import com.ksv.DAO.*;
import com.ksv.Log;

public class DbPrinter extends Printer implements IPrinter {

    public DbPrinter(Log log) {
        super(log);
    }

    public void print(Log log){
        DAOLogRecord dao = new DAOLogRecord();
        dao.saveAll(log);
        logger.info("В базу данных загружено "+ log.size() +" записей.");
    }
}
