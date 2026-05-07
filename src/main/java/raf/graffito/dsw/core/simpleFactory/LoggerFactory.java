package raf.graffito.dsw.core.simpleFactory;

public class LoggerFactory {

    public static ILogger createLogger(String loggerType) {
        switch (loggerType) {
            case "console":
                return new ConsoleLogger();
            case "file":
                return new FileLogger();
            default:
                throw new IllegalArgumentException("Invalid logger type: " + loggerType);
        }
    }
}
