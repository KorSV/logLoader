package com.ksv;

import com.ksv.exceptions.LogRecordException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Log {
    private List<LogRecord> lines = new ArrayList<LogRecord>();
    private int position = -1;
    private int length;

    private static Logger logger = LogManager.getLogger(Log.class);

    public Log(List<String> lines) {
        for (String line : lines) {
            if ( !line.isEmpty() ){
                try {
                    this.lines.add(new LogRecord(line));
                } catch (LogRecordException e) {
                    logger.warn(e);
                }
            }
        }
        setLength();
    }

    public Log(){}

    public void setPosition(int position) {
        this.position = position;
    }

    public int size() {
        return lines.size();
    }

    public void setLength() {
        this.length = lines.size();
    }

    public List<LogRecord> getLines() {
        return lines;
    }

    public int getPosition() {
        return position;
    }

    public LogRecord next(){
        setPosition(this.position+1);
        if (getPosition() <= lines.size()) {
            return lines.get(this.position);
        }
        return null;
    }

    public void findLastPositionSavedInDB(LogRecord lastLogRecordFromDb){
        position = 0;
        int lastPosition = -1;
        for (LogRecord logRecord : lines) {
            /*if (logRecord.getDate().equals(lastLogRecordFromDb.getDate()) &&
                logRecord.getEvent().equals(lastLogRecordFromDb.getEvent()) &&
                logRecord.getId().equals(lastLogRecordFromDb.getId()))*/
            if (logRecord.equals(lastLogRecordFromDb))
            {
                lastPosition = position;
            }
            position++;
        }
        this.position = lastPosition;
    }

    public void add(LogRecord logRecord){
        this.lines.add(logRecord);
    }

}
