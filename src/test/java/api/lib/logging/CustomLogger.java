package api.lib.logging;

import io.qameta.allure.Allure;
import lombok.extern.log4j.Log4j2;
import api.lib.properties.PropertyFileReader;
import org.apache.logging.log4j.message.ParameterizedMessage;

@Log4j2
public class CustomLogger {

    private static final boolean enableAllure = Boolean.parseBoolean(
            PropertyFileReader.containsKey("ENABLE_ALLURE_LOGGING")
                    ? PropertyFileReader.getPropertyValue("ENABLE_ALLURE_LOGGING")
                    : "false"
    );

    public static void info(String message, Object... args) {
        String formatted = format(message, args);
        log.info(formatted);
        if (enableAllure) Allure.step("[INFO] " + formatted);
    }

    public static void warn(String message, Object... args) {
        String formatted = format(message, args);
        log.warn(formatted);
        if (enableAllure) Allure.step("[WARN] " + formatted);
    }

    public static void error(String message, Object... args) {
        String formatted = format(message, args);
        log.error(formatted);
        if (enableAllure) Allure.step("[ERROR] " + formatted);
    }

    public static void debug(String message, Object... args) {
        String formatted = format(message, args);
        log.debug(formatted);
        if (enableAllure) Allure.step("[DEBUG] " + formatted);
    }

    private static String format(String message, Object... args) {
        return new ParameterizedMessage(message, args).getFormattedMessage();
    }
}