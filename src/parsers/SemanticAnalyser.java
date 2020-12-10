package parsers;

import java.util.logging.Logger;
import logger.LogMsg;
import java.util.HashMap;

public class SemanticAnalyser {

    static final Logger logger = Logger.getLogger(SemanticAnalyser.class.getName());

    private SemanticAnalyser() {}

    public static Integer getInteger(HashMap<String, String> variableMap, String token){
        if (variableMap == null || token == null){
            logger.severe(LogMsg.INVALID_ARGUMENTS.msg);
            return null;
        }
        try{
            String strVal = variableMap.get(token);
            Integer value = Integer.parseInt(strVal);
            return value;
        }
        catch (NumberFormatException ex){
            logger.severe(LogMsg.INVALID_CONFIG_DATA.msg + "\n" + token);
        }
        return null;
    }

    public static Double getDouble(HashMap<String, String> variableMap, String token){
        if (variableMap == null || token == null){
            logger.severe(LogMsg.INVALID_ARGUMENTS.msg);
            return null;
        }
        try{
            String strVal = variableMap.get(token);
            Double value = Double.parseDouble(strVal);
            return value;
        }
        catch (NumberFormatException | NullPointerException ex){
            logger.severe(LogMsg.INVALID_CONFIG_DATA.msg + "\n" + token);
        }
        return null;
    }

    public static String getString(HashMap<String, String> variableMap, String token){
        if (variableMap == null || token == null){
            logger.severe(LogMsg.INVALID_ARGUMENTS.msg);
            return null;
        }
        try{
            String value = variableMap.get(token);
            if (value == null){
                logger.severe(LogMsg.INVALID_CONFIG_DATA.msg + "\n" + token);
            }
            return value;
        }
        catch (NumberFormatException ex){
            logger.severe(LogMsg.INVALID_CONFIG_DATA.msg + "\n" + token);
        }
        return null;
    }
}
