package com.ksv;

import com.ksv.printers.ConsolePrinter;
import com.ksv.printers.DbPrinter;
import com.ksv.printers.IPrinter;
import com.ksv.readers.IReader;
import com.ksv.readers.ReadFromFile;
import com.ksv.watcher.IWatcher;
import com.ksv.watcher.Watcher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Application {
    public static String path;
    public static String file;
    public static String output;
    public static String type;

    private static Logger logger = LogManager.getLogger(Application.class);

    public Application(String path, String file, String output, String type) {
        this.path = path;
        this.file = file;
        this.output = output;
        this.type = type;
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

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public static String getType() {
        return type;
    }

    public static void setType(String type) {
        Application.type = type;
    }

    public static void run(){
        logger.info("Start application logLoader ........");



        if (type.equals("all")){
            logger.info("Обработка файла: "+file);

        }
        IReader reader = new ReadFromFile(path+"/"+file);
        Log log = reader.read();
        IPrinter printer;
        if (output.equals("console")) {
            printer = new ConsolePrinter(log);
        }
        else if (output.equals("db")) {
            printer = new DbPrinter(log);
        }else{
            printer = new ConsolePrinter(log);
        }
        printer.printMissing();

        if (type.equals("all")) {
           System.exit(0);
        }else if (type.equals("watch")){
            IWatcher watcher = new Watcher(path, file, log);
            watcher.watch();
        }
    }
}
