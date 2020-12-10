package grammar;

public class Grammar {

    public enum Reader{
        filePath("filePath"),
        nProcBytes("nProcBytes"),
        separator("="),
        endLine(";");


        final public String lexeme;
        Reader(String lexeme) {
            this.lexeme = lexeme;
        }
    }

    public enum Writer{
        filePath("filePath"),
        separator("="),
        endLine(";");

        final public String lexeme;

        Writer(String lexeme) {
            this.lexeme = lexeme;
        }
    }

    public enum Encoder {
        rightBorder("rightBorder"),
        leftBorder("leftBorder"),
        eps("eps"),
        separator("="),
        endLine(";");

        final public String lexeme;

        Encoder(String lexeme) {
            this.lexeme = lexeme;
        }
    }

    public enum Manager {
        cfgReader("cfgReader"),
        cfgWriter("cfgWriter"),
        cfgExecutor("cfgExecutor"),
        modeExecutor("modeExecutor"),
        encodeMode("encodeMode"),
        decodeMode("decodeMode"),
        separator("="),
        endLine(";")    ;

        final public String lexeme;
        Manager(String lexeme){
            this.lexeme = lexeme;
        }
    }

}
