package com.ksv;

import com.ksv.exceptions.LogRecordException;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class LogRecord {
    private int logId;
    private String id;

    private String dt;
    private String tm;
    private Timestamp date;

    private String event;
    private String ip;
    private String client;
    private String err;
    private String  strErr;
    private String db;
    private String seg;
    private String file;
    private int score;
    private int reclist;
    private int errs;
    private int skip;
    private int total;
    private String qmask;
    private String cmask;
    private String rmask;
    private int dbsize;
    private int fpoints;
    private String ftxt103;
    private String ftxt105;
    private Date ftxt107;
    private String ftxt110;
    private String ftxt111;
    private String ftxt112;
    private Date ftxt113;
    private String ftxt116;
    private String ftxt117;
    private String ftxt118;
    private String ftxt121;
    private String ftxt465;
    private String ftxt459;


    public LogRecord() {
    }

    public LogRecord(String line) throws LogRecordException {
        for (String elem : line.split("(?<!\\\\)[\\|]")) {
            String[] propVal = elem.split("(?<!\\\\)[\\,]",2);
            if (propVal.length == 2) {

                String prop = propVal[0];
                Object val = propVal[1];


                if (prop.equals("DT")) {

                    setDt((String) val);


                }
                if (prop.equals("TM")) {
                    setTm((String) val);

                    String dt = getDt() + " " + (String) val;
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    java.util.Date parsed = null;
                    try {
                        parsed = format.parse(dt);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Timestamp date = new Timestamp(parsed.getTime());
                    setDate(date);

                }
                if (prop.equals("EVNT")) {
                    setEvent((String) val);
                }
                if (prop.equals("IP")) {
                    setIp((String) val);
                }
                if (prop.equals("CLNT")) {
                    setClient((String) val);
                }
                if (prop.equals("ID")) {
                    setId((String) val);
                }
                if (prop.equals("ERR")) {
                    setErr((String) val);
                }
                if (prop.equals("STRERR")) {
                    setStrErr((String) val);
                }
                if (prop.equals("DB")) {
                    setDb((String) val);
                }
                if (prop.equals("SEG")) {
                    setSeg((String) val);
                }
                if (prop.equals("FILE")) {
                    setFile((String) val);
                }
                if (prop.equals("SCORE")) {
                    setScore(Integer.valueOf((String) val));
                }
                if (prop.equals("RECLIST")) {
                    setReclist(Integer.valueOf((String) val));
                }
                if (prop.equals("ERRS")) {
                    setErrs(Integer.valueOf((String) val));
                }
                if (prop.equals("SKIP")) {
                    setSkip(Integer.valueOf((String) val));
                }
                if (prop.equals("TOTAL")) {
                    setTotal(Integer.valueOf((String) val));
                }
                if (prop.equals("QMASK")) {
                    setQmask((String) val);
                }
                if (prop.equals("RMASK")) {
                    setRmask((String) val);
                }
                if (prop.equals("CMASK")) {
                    setCmask((String) val);
                }
                if (prop.equals("DBSIZE")) {
                    setDbsize(Integer.valueOf((String) val));
                }
                if (prop.equals("FPOINTS")) {
                    setFpoints(Integer.valueOf((String) val));
                }
                if (prop.equals("FTXT465")) {
                    setFtxt465((String) val);
                }
                if (prop.equals("FTXT459")) {
                    setFtxt459((String) val);
                }
                if (prop.equals("FTXT103")) {
                    setFtxt103((String) val);
                }
                if (prop.equals("FTXT105")) {
                    setFtxt105((String) val);
                }
                if (prop.equals("FTXT107")) {
                    String dt = (String) val;
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    SimpleDateFormat formatAlt = new SimpleDateFormat("dd.MM.yyyy");
                    java.util.Date parsed = null;
                    try {
                        parsed = format.parse(dt);
                    } catch (ParseException e) {
                        try {
                            parsed = formatAlt.parse(dt);
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                        }
                    }
                    Date date = new Date(parsed.getTime());
                    setFtxt107(date);
                }
                if (prop.equals("FTXT110")) {
                    setFtxt110((String) val);
                }
                if (prop.equals("FTXT111")) {
                    setFtxt111((String) val);
                }
                if (prop.equals("FTXT112")) {
                    setFtxt112((String) val);
                }
                if (prop.equals("FTXT113")) {
                    String dt = (String) val;
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    SimpleDateFormat formatAlt = new SimpleDateFormat("dd.MM.yyyy");
                    java.util.Date parsed = null;
                    try {
                        parsed = format.parse(dt);
                    } catch (ParseException e) {
                        try {
                            parsed = formatAlt.parse(dt);
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                        }
                    }
                    Date date = new Date(parsed.getTime());
                    setFtxt113(date);
                }
                if (prop.equals("FTXT116")) {
                    setFtxt116((String) val);
                }
                if (prop.equals("FTXT117")) {
                    setFtxt117((String) val);
                }
                if (prop.equals("FTXT118")) {
                    setFtxt118((String) val);
                }
                if (prop.equals("FTXT121")) {
                    setFtxt121((String) val);
                }
            }
        } if (this.date == null || this.event == null ){
            throw new LogRecordException("Ошибка преобразования строки:" + line + " строка пропущена!!!");
        }
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public String getTm() {
        return tm;
    }

    public void setTm(String tm) {
        this.tm = tm;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public String getFtxt121() {
        return ftxt121;
    }

    public void setFtxt121(String ftxt121) {
        this.ftxt121 = ftxt121;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }

    public String getStrErr() {
        return strErr;
    }

    public void setStrErr(String strErr) {
        this.strErr = strErr;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getSeg() {
        return seg;
    }

    public void setSeg(String seg) {
        this.seg = seg;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getReclist() {
        return reclist;
    }

    public void setReclist(int reclist) {
        this.reclist = reclist;
    }

    public int getErrs() {
        return errs;
    }

    public void setErrs(int errs) {
        this.errs = errs;
    }

    public int getSkip() {
        return skip;
    }

    public void setSkip(int skip) {
        this.skip = skip;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getQmask() {
        return qmask;
    }

    public void setQmask(String qmask) {
        this.qmask = qmask;
    }

    public String getCmask() {
        return cmask;
    }

    public void setCmask(String cmask) {
        this.cmask = cmask;
    }

    public String getRmask() {
        return rmask;
    }

    public void setRmask(String rmask) {
        this.rmask = rmask;
    }

    public int getDbsize() {
        return dbsize;
    }

    public void setDbsize(int dbsize) {
        this.dbsize = dbsize;
    }

    public int getFpoints() {
        return fpoints;
    }

    public void setFpoints(int fpoints) {
        this.fpoints = fpoints;
    }

    public String getFtxt103() {
        return ftxt103;
    }

    public void setFtxt103(String ftxt103) {
        this.ftxt103 = ftxt103;
    }

    public String getFtxt105() {
        return ftxt105;
    }

    public void setFtxt105(String ftxt105) {
        this.ftxt105 = ftxt105;
    }

    public Date getFtxt107() {
        return ftxt107;
    }

    public void setFtxt107(Date ftxt107) {
        this.ftxt107 = ftxt107;
    }

    public String getFtxt110() {
        return ftxt110;
    }

    public void setFtxt110(String ftxt110) {
        this.ftxt110 = ftxt110;
    }

    public String getFtxt111() {
        return ftxt111;
    }

    public void setFtxt111(String ftxt111) {
        this.ftxt111 = ftxt111;
    }

    public String getFtxt112() {
        return ftxt112;
    }

    public void setFtxt112(String ftxt112) {
        this.ftxt112 = ftxt112;
    }

    public Date getFtxt113() {
        return ftxt113;
    }

    public void setFtxt113(Date ftxt113) {
        this.ftxt113 = ftxt113;
    }

    public String getFtxt116() {
        return ftxt116;
    }

    public void setFtxt116(String ftxt116) {
        this.ftxt116 = ftxt116;
    }

    public String getFtxt117() {
        return ftxt117;
    }

    public void setFtxt117(String ftxt117) {
        this.ftxt117 = ftxt117;
    }

    public String getFtxt118() {
        return ftxt118;
    }

    public void setFtxt118(String ftxt118) {
        this.ftxt118 = ftxt118;
    }

    public String getFtxt465() {
        return ftxt465;
    }

    public void setFtxt465(String ftxt465) {
        this.ftxt465 = ftxt465;
    }

    public String getFtxt459() {
        return ftxt459;
    }

    public void setFtxt459(String ftxt459) {
        this.ftxt459 = ftxt459;
    }

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof LogRecord)) {return false;}
        LogRecord logRecord = (LogRecord) obj;
        if (equalField(this.id,logRecord.id)
            && equalField(this.date,logRecord.date)
            && equalField(this.event,logRecord.event)
            && equalField(this.ip,logRecord.ip)
            && equalField(this.client,logRecord.client)
            && equalField(this.err,logRecord.err)
            && equalField(this.strErr,logRecord.strErr)
            && equalField(this.db,logRecord.db)
            && equalField(this.seg,logRecord.seg)
            && equalField(this.file,logRecord.file)
            && this.score == logRecord.score
            && this.reclist == logRecord.reclist
            && this.errs == logRecord.errs
            && this.skip == logRecord.skip
            && this.total == logRecord.total
            && equalField(this.qmask,logRecord.qmask)
            && equalField(this.cmask,logRecord.cmask)
            && equalField(this.rmask,logRecord.rmask)
            && this.dbsize == logRecord.dbsize
            && this.fpoints == logRecord.fpoints
            && equalField(this.ftxt103,logRecord.ftxt103)
            && equalField(this.ftxt105,logRecord.ftxt105)
            && equalField(this.ftxt107,logRecord.ftxt107)
            && equalField(this.ftxt110,logRecord.ftxt110)
            && equalField(this.ftxt111,logRecord.ftxt111)
            && equalField(this.ftxt112,logRecord.ftxt112)
            && equalField(this.ftxt113,logRecord.ftxt113)
            && equalField(this.ftxt116,logRecord.ftxt116)
            && equalField(this.ftxt117,logRecord.ftxt117)
            && equalField(this.ftxt118,logRecord.ftxt118)
            && equalField(this.ftxt121,logRecord.ftxt121)
            && equalField(this.ftxt465,logRecord.ftxt465)
            && equalField(this.ftxt459,logRecord.ftxt459)){
            return true;
        }
        return false;
    }

    public boolean shortEquals(Object obj) {
        if (!(obj instanceof LogRecord)) {return false;}
        LogRecord logRecord = (LogRecord) obj;
        if (equalField(this.id,logRecord.id)
                && equalField(this.date,logRecord.date)
                && equalField(this.event,logRecord.event)){
            return true;
        }
        return false;
    }

    private static final boolean equalField(Object o1, Object o2){
        if ((o1 == null && o2 == null)||(o1 !=null && o2 != null && o1.equals(o2))){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String toString() {
        return "LogRecord{" +
                "logId=" + logId +
                ", id='" + id + '\'' +
                ", dt='" + dt + '\'' +
                ", tm='" + tm + '\'' +
                ", date=" + date +
                ", event='" + event + '\'' +
                ", ip='" + ip + '\'' +
                ", client='" + client + '\'' +
                ", err='" + err + '\'' +
                ", strErr='" + strErr + '\'' +
                ", db='" + db + '\'' +
                ", seg='" + seg + '\'' +
                ", file='" + file + '\'' +
                ", score=" + score +
                ", reclist=" + reclist +
                ", errs=" + errs +
                ", skip=" + skip +
                ", total=" + total +
                ", qmask='" + qmask + '\'' +
                ", cmask='" + cmask + '\'' +
                ", rmask='" + rmask + '\'' +
                ", dbsize=" + dbsize +
                ", fpoints=" + fpoints +
                ", ftxt103='" + ftxt103 + '\'' +
                ", ftxt105='" + ftxt105 + '\'' +
                ", ftxt107=" + ftxt107 +
                ", ftxt110='" + ftxt110 + '\'' +
                ", ftxt111='" + ftxt111 + '\'' +
                ", ftxt112='" + ftxt112 + '\'' +
                ", ftxt113=" + ftxt113 +
                ", ftxt116='" + ftxt116 + '\'' +
                ", ftxt117='" + ftxt117 + '\'' +
                ", ftxt118='" + ftxt118 + '\'' +
                ", ftxt121='" + ftxt121 + '\'' +
                ", ftxt465='" + ftxt465 + '\'' +
                ", ftxt459='" + ftxt459 + '\'' +
                '}';
    }
}
