package com.ksv.readers;

import com.ksv.Log;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class ReadFromFile implements IReader {
    public String source;

    FileInputStream input;

    private static Logger logger = LogManager.getLogger(ReadFromFile.class);

    public ReadFromFile(String source) {
        this.source = source;
    }

    /*public Log read() {
        Path source =  Paths.get(this.source);
        CharsetDecoder decoder = Charset.forName("utf-8").newDecoder();
        //decoder.onMalformedInput(CodingErrorAction.REPLACE);
        //decoder.charset();
        //Charset charset = Charset.forName("utf-8");
        List<String> lines = new ArrayList<String>();
        try {
            lines = Files.readAllLines(source);
            System.out.println(lines.get(lines.size()-1));
            return null;//new Log(lines);
        } catch (IOException e){
            System.out.println( e );
            System.out.println( lines.size() );
            logger.error(e);
        }
    return null;
    }*/

    @Override
    public Log read() {
        try {
            input = new FileInputStream(new File(source));
            CharsetDecoder decoder = Charset.forName("utf-8").newDecoder();
            decoder.onMalformedInput(CodingErrorAction.IGNORE);
            InputStreamReader reader = new InputStreamReader(input, decoder);
            BufferedReader bufferedReader = new BufferedReader(reader);
            List<String> lines = new ArrayList<String>();
            String line = bufferedReader.readLine();
            while (line != null){
                lines.add(line);
                line = bufferedReader.readLine();
            }
            List<String> linesPreobr = new ArrayList<String>();
            for (int i = 0; i < lines.size(); i++) {
                String linePreobr = "";
                if (i==0) {
                    linePreobr = lines.get(i);
                    linesPreobr.add(linePreobr);
                }else if (lines.get(i).length()>1 && lines.get(i).substring(0,2).equals("\\n")){
                        String ln = lines.get(i-1);
                        linesPreobr.remove(linesPreobr.size()-1);
                    while(lines.get(i).length()>1 && lines.get(i).substring(0,2).equals("\\n") )
                        {
                            linePreobr = linePreobr + lines.get(i);
                            if (i<lines.size()-1) {
                                i++;
                            }else{break;}
                        }
                        if (i<lines.size()-1){
                        i--;}
                        linePreobr = ln + linePreobr;
                        linesPreobr.add(linePreobr);
                }else{
                    linePreobr = lines.get(i);
                    linesPreobr.add(linePreobr.replaceAll("\\p{C}", ""));
                }
            }

            return new Log(linesPreobr);
        } catch (FileNotFoundException e){
            logger.error(e);
        } catch (IOException e){
            logger.error(e);
        }
        return null;
    }
}
