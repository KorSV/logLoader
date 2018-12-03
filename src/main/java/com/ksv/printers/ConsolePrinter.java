package com.ksv.printers;

import com.ksv.Log;
import com.ksv.LogRecord;

public class ConsolePrinter extends Printer implements IPrinter {


    public ConsolePrinter( Log log) {
        super( log);
    }

    public void print(Log log){
        for (LogRecord logRecord : log.getLines()) {
            System.out.print("|" + padRight(logRecord.getDt(), 15) + "|");
            System.out.print(padRight(logRecord.getTm(), 15) + "|");
            System.out.print(padRight(logRecord.getEvent(), 15) + "|");
            System.out.print(padRight(logRecord.getClient(), 10) + "|");
            System.out.println(padRight(logRecord.getIp(), 20) + "|");
        }
    }

    public static String padRight(String s, int n) {
        if (s == null) {
            s = "-";
        }
        return String.format("%1$-" + n + "s", s);
    }
}
