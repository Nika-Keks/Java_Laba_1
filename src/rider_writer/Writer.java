package rider_writer;

import grammar.Grammar;
import logger.LogMsg;
import parsers.SemanticAnalyser;
import parsers.SyntacticalAnalyser;

import java.util.logging.Logger;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class Writer {
    String filePath;
    FileOutputStream bFile;
    static final Logger logger = Logger.getLogger(Writer.class.getName());

    public Writer(){
    }

    public boolean setConfig(String cfgPath){
        HashMap<String,String> cfgParam =
                SyntacticalAnalyser.getValidExpr(cfgPath,
                        Grammar.Writer.separator.lexeme,
                        Grammar.Writer.endLine.lexeme);

        filePath = SemanticAnalyser.getString(cfgParam, Grammar.Writer.filePath.lexeme);
        if (filePath == null){
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
            bFile = new FileOutputStream(file);
            return true;
        } catch (FileNotFoundException ex) {
            logger.severe(LogMsg.FILE_NOT_FOUND.msg);
        }
        return false;
    }

    public void writeFile(byte[] data){
        if (data == null)
            return;
        try{
            bFile.write(data);
        } catch (IOException ex) {
            logger.severe(LogMsg.ERROR_FILE_WRITING.msg);
        }
    }

}
