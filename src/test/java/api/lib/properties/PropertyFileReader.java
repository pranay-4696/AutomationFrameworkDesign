package api.lib.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileReader {

    private static final String BASE_PATH = System.getProperty("user.dir");
    private static final String CONFIG_DIR = "/src/test/resources/environments";
    private static final String GLOBAL_FILE = CONFIG_DIR + "/application.properties";

    private static final Properties globalProps = new Properties();
    private static final Properties envProps = new Properties();
    private static boolean initialized = false;

    private static void loadAllProperties() {
        if (initialized) return;

        try {
            // Load global application.properties
            try (FileInputStream globalStream = new FileInputStream(BASE_PATH + GLOBAL_FILE)) {
                globalProps.load(globalStream);
            }

            // Read ENVIRONMENT key
            String environment = globalProps.getProperty("ENVIRONMENT");
            if (environment == null || environment.isEmpty()) {
                throw new RuntimeException("Missing 'ENVIRONMENT' key in application.properties");
            }

            // Load env-specific file
            String envFile = CONFIG_DIR + "/application-" + environment + ".properties";
            try (FileInputStream envStream = new FileInputStream(BASE_PATH + envFile)) {
                envProps.load(envStream);
            }

            initialized = true;

        } catch (IOException e) {
            throw new RuntimeException("Failed to load property files", e);
        }
    }

    public static String getPropertyValue(String key) {
        loadAllProperties();
        // env-specific props override global
        return envProps.getProperty(key, globalProps.getProperty(key));
    }

    public static String getEnv() {
        loadAllProperties();
        return globalProps.getProperty("ENVIRONMENT");
    }

    public static boolean containsKey(String key) {
        loadAllProperties();
        return envProps.containsKey(key) || globalProps.containsKey(key);
    }
}