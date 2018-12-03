package com.ksv.printers;

import com.ksv.DAO.DAOLogRecord;
import com.ksv.Log;
import com.ksv.LogRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public abstract class Printer implements IPrinter{

    public Log log;

    public static Logger logger = LogManager.getLogger(Printer.class);

    public Printer( Log log) {
        this.log = log;
    }

    public void printAll() {
        print(log);
    }

    /* Напечатает все отсутствующие в БД*/
    public void printMissing(){
        logger.info("Проверка расхождения файла и БД!!!");
        LogRecord logRecord = new DAOLogRecord().getLastRecord();
        if (logRecord == null) {
            logger.info("В базе данных нет ни одной записи. Загрузка в БД всего файла ......");
            printAll();
        }else{
            log.findLastPositionSavedInDB(logRecord);
            if (log.getPosition() == log.size()-1) {
                logger.info("Файл не изменился.");
            }
            else if (log.getPosition() == -1){
                logger.info("В файле не найдена последняя запись из БД. " +
                               "Скорее всего в момент простоя произошло переключение логов. " +
                               "Необходимо проверить все ли данные из последнего заархивированного лога загружены в БД. " +
                               "Загрузка в БД всего файла .......");
                printAll();
            }else {
                Log missingLog = new Log();
                while (log.getPosition() < log.size() - 1) {
                    missingLog.add(log.next());
                }
                logger.info("В файле найдено " + missingLog.size() + " новых записей.");
                logger.info("Загрузка записей в БД .......");
                print(missingLog);
            }
        }
    }

    public void printAdded(Log oldLog) {
        Log logAdded = new Log();
        logger.info("В файл добавлено "+(log.size()-oldLog.size())+" записей.");
        int startPos = log.size() - (log.size() - oldLog.size());
        log.setPosition(startPos - 1);
        for (int i = log.getPosition() + 1; i < log.size(); i++) {
            logAdded.add(log.next());
        }
        print(logAdded);
    }

    abstract void print(Log log);

}
