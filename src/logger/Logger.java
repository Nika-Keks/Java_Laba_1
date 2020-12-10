package logger;

import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    static FileWriter logFile;
    private Logger(){};


    static public void init(String logFilePath){
        if (logFile != null)
            return;
        try{
            logFile = new FileWriter(logFilePath);
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    static public void clos(){
        try {
            logFile.close();
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    static public void log(String srt){
        try{
            if (logFile == null) return;
            logFile.write(srt + '\n');
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }

    }
    static public void log(Errors error){
        try{
            if (logFile == null) return;
            logFile.write(error.msg + '\n');
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
