package com.ksv.watcher;

import com.ksv.Application;
import com.ksv.Log;
import com.ksv.printers.ConsolePrinter;
import com.ksv.printers.DbPrinter;
import com.ksv.printers.IPrinter;
import com.ksv.readers.IReader;
import com.ksv.readers.ReadFromFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

import java.io.IOException;
import java.nio.file.*;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

public class Watcher implements IWatcher {

    private String path;
    private String file;
    private Log oldLog;
    private static Logger logger = LogManager.getLogger(Watcher.class);
    private Session session;

    public Watcher() {
    }

    public Watcher( String path, String file, Log oldLog, Session session) {
        this.path = path;
        this.file = file;
        this.oldLog = oldLog;
        this.session = session;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }


    @Override
    public void watch() {
        try {
            WatchService watcher = FileSystems.getDefault().newWatchService();
            Path dir = Paths.get(getPath());
            dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
            logger.info("Watch Service registered for dir: " + dir.getFileName());

            while (true) {
                WatchKey key;
                try {
                    key = watcher.take();
                } catch (InterruptedException ex) {
                    return;
                }

                for (WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();

                    @SuppressWarnings("unchecked")
                    WatchEvent<Path> ev = (WatchEvent<Path>) event;
                    Path fileName = ev.context();

                    if (kind == ENTRY_MODIFY &&
                            fileName.toString().equals(getFile())) {
                        changeFile();
                    }
                }

                boolean valid = key.reset();
                if (!valid) {
                    break;
                }
            }

        } catch (IOException ex) {
            logger.error(ex);
        }
    }

    public void changeFile() {
        logger.info("Файл лога изменен!!!");
        IReader reader = new ReadFromFile(getPath()+"/"+getFile());
        Log newLog = reader.read();
        IPrinter printer;

        if ( Application.output.equals("console") ) {

        printer = new ConsolePrinter( newLog, session);
        }
        else if (Application.output.equals("db")){
            printer = new DbPrinter( newLog, session);
        } else{
            printer = new ConsolePrinter( newLog, session);
        }

        if (newLog.size()>oldLog.size()){
            printer.printAdded(oldLog);

        } else if (newLog.size() == oldLog.size()){
            logger.info("!!!Количество записей не поменялось!!!");
        }else{
            logger.info("!!!Произошло переключение логов!!!");
            printer.printAll();
        }
        oldLog = newLog;
        }

}
