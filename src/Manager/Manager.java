package Manager;

import encoder.*;
import grammar.Grammar;
import logger.LogMsg;
import parsers.SemanticAnalyser;
import parsers.SyntacticalAnalyser;
import rider_writer.Reader;
import rider_writer.Writer;

import java.util.HashMap;
import java.util.logging.Logger;

public class Manager {
    String cfgReader;
    String cfgWriter;
    String cfgExecutor;
    String modeExecutor;
    Reader reader;
    Writer writer;
    Encoder encoder;
    Decoder decoder;
    static final Logger logger = Logger.getLogger(Manager.class.getName());

    public boolean setConfig(String cfgPath){
        HashMap<String ,String> variableMap = SyntacticalAnalyser.getValidExpr(cfgPath,
                Grammar.Manager.separator.lexeme,
                Grammar.Manager.endLine.lexeme);

        cfgReader = SemanticAnalyser.getString(variableMap, Grammar.Manager.cfgReader.lexeme);
        cfgWriter = SemanticAnalyser.getString(variableMap, Grammar.Manager.cfgWriter.lexeme);
        cfgExecutor = SemanticAnalyser.getString(variableMap, Grammar.Manager.cfgExecutor.lexeme);
        modeExecutor = SemanticAnalyser.getString(variableMap, Grammar.Manager.modeExecutor.lexeme);
        if (cfgWriter == null || cfgReader == null || modeExecutor == null){
            logger.severe(LogMsg.INVALID_CONFIG_DATA.msg);
            return false;
        }
        logger.info(LogMsg.SUCCESS.msg);
        return true;
    }

    public boolean createConveyor(){
        reader = new Reader();
        if (!reader.setConfig(cfgReader) || !reader.openFile())
            return false;
        writer = new Writer();
        if (!writer.setConfig(cfgWriter) || !writer.openFile())
            return false;

        if (modeExecutor.equals(Grammar.Manager.decodeMode.lexeme)){
            decoder = new Decoder();
        }
        else if (modeExecutor.equals(Grammar.Manager.encodeMode.lexeme)) {
            encoder = new Encoder();
            return encoder.setConfig(cfgExecutor);
        }
        logger.info(LogMsg.SUCCESS.msg);
        return true;
    }

    public void execute(){
        byte[] inData = reader.readFile();
        while (inData != null){
            byte[] outData;
            if (modeExecutor.equals(Grammar.Manager.encodeMode.lexeme)){
               outData =  encoder.encode(inData);
            }
            else if (modeExecutor.equals(Grammar.Manager.decodeMode.lexeme)){
                outData = decoder.decode(inData);
            }
            else{
                outData = inData;
            }
            writer.writeFile(outData);
            inData = reader.readFile();
        }
    }
}
