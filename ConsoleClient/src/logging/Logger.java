package logging;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class Logger {
    private final static PrintWriter logger = new PrintWriter(
            new OutputStreamWriter(System.out, StandardCharsets.UTF_8), true);

    private Logger() {
    }

    public static PrintWriter getLogger() {
        return logger;
    }
}
