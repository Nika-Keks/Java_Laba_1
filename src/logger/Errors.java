package logger;

public enum Errors {
    DECODING_DATA_READING_FAILURE(1, "DECODING_DATA_READING_FAILURE"),
    NO_CONFIG_FILE(2, "NO_CONFIG_FILE"),
    ERROR_FILE_READING(3, "ERROR_FILE_READING"),
    FILE_NOT_FOUND(4, "FILE_NOT_FOUND"),
    INVALID_CONFIG_DATA(5, "INVALID_CONFIG_DATA"),
    INVALID_COMMAND_LINE_PARAMETERS(6, "INVALID_COMMAND_LINE_PARAMETERS");

    public final int code;
    public final String msg;

    Errors(int i, String errMsg) {
        this.msg = errMsg;
        code = i;
    }

}

