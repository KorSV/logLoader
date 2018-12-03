package com.ksv;

import org.apache.log4j.BasicConfigurator;

public class Main{
    public static void main(String[] args ){

            //System.setProperty("org.jboss.logging.provider", "log4j");
       // System.setProperty( "com.mchange.v2.log.Log4jMLog.DEFAULT_CUTOFF_LEVEL", "WARNING" );
         System.setProperty("com.mchange.v2.log.MLog","com.mchange.v2.log.log4j.Log4jMLog");
        //System.setProperty("com.mchange.v2.log.MLog", "com.mchange.v2.log.FallbackMLog");

        BasicConfigurator.configure();


        Application.path = args[0];
        Application.file = args[1];
        Application.output = args[2];
        Application.type = args[3];
        Application.run();
    }
}
