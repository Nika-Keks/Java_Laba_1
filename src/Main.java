import Manager.Manager;
import logger.Errors;
import logger.LogMsg;
//import logger.Logger;
import java.util.logging.*;

public class Main {
    static Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        if (args.length != 1) {
            logger.severe(LogMsg.INVALID_ARGUMENTS.msg);
            return;
        }
        Manager manager = new Manager();
        if (!manager.setConfig(args[0]) || !manager.createConveyor()){
            return;
        }
        manager.execute();
    }
}
