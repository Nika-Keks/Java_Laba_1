package rider_writer;
import grammar.Grammar;
import logger.*;
import parsers.SemanticAnalyser;
import parsers.SyntacticalAnalyser;

import java.util.logging.Logger;
import java.io.*;
import java.util.HashMap;

public class Reader {
    private String filePath;
    private Integer nProcBytes;
    int fileSize;
    int nReadData;
    FileInputStream bFile;
    static final Logger logger = Logger.getLogger(Reader.class.getName());

    public boolean setConfig(String cfgPath){
        HashMap<String,String> cfgParam =
                SyntacticalAnalyser.getValidExpr(cfgPath,
                        Grammar.Reader.separator.lexeme,
                        Grammar.Reader.endLine.lexeme);


        filePath = SemanticAnalyser.getString(cfgParam, Grammar.Reader.filePath.lexeme);
        nProcBytes = SemanticAnalyser.getInteger(cfgParam, Grammar.Reader.nProcBytes.lexeme);
        if (nProcBytes == null || filePath == null){
            logger.severe(LogMsg.INVALID_CONFIG_DATA.msg);
            return false;
        }
        logger.info(LogMsg.SUCCESS.msg);
        return true;
    }

    public boolean openFile(){
        if (bFile != null)
            return false;

        try{
            File file = new File(filePath);
            if (!file.exists())
                throw new FileNotFoundException("file (" + filePath + ") does not exist");

            fileSize = (int) file.length();
            bFile = new FileInputStream(file);

            return true;
        }
        catch(FileNotFoundException ex){
            System.out.println(ex.getMessage());
            logger.severe(LogMsg.FILE_NOT_FOUND.msg);
        }
        return false;
    }

    public byte[] readFile(){
        if (bFile == null)
            return  null;

        try{
            byte[] data;
            if (nProcBytes < fileSize - nReadData){
                data = new byte[nProcBytes];
                bFile.read(data);
                nReadData += nProcBytes;
            }
            else{
                data = new byte[fileSize - nReadData];
                bFile.read(data);
                bFile.close();
                bFile = null;
            }
            return data;
        } catch (IOException ex) {
            logger.severe(LogMsg.ERROR_FILE_READING.msg);
        }
        return null;
    }
}
