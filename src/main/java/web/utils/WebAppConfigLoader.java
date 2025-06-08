package web.utils;

import java.util.Objects;
import java.util.Properties;

public class WebAppConfigLoader {

    private final Properties PROPERTIES;
    private static WebAppConfigLoader instance;
    private static final String WEB_PORTAL_PROPERTIES_FILE = "./src/test/resources/webapp.properties";

    private WebAppConfigLoader() {
        PROPERTIES = WebPropertyUtils.loadProperties(WEB_PORTAL_PROPERTIES_FILE);
    }

    public static WebAppConfigLoader getInstance() {
        if (instance == null) {
            synchronized (WebAppConfigLoader.class) {
                if (instance == null) {
                    instance = new WebAppConfigLoader();
                }
            }
        }
        return instance;
    }

    public String getBrowser() {
        return getWebAppPropertyValue("browser");
    }

    public String getRunMode() {
        return getWebAppPropertyValue("runmode");
    }

    public String getOrangeHrmWebUrl() {
        return getWebAppPropertyValue("orangehrm.url");
    }

    public String getOrangeHrmUsername() {
        return getWebAppPropertyValue("orangehrm.username");
    }

    public String getOrangeHrmPassword() {
        return getWebAppPropertyValue("orangehrm.password");
    }

    private String getWebAppPropertyValue(String webAppPropertyKey) {
        String propertyValue = PROPERTIES.getProperty(webAppPropertyKey);
        Objects.requireNonNull(propertyValue,
                "Web App Property Key: " + webAppPropertyKey + " not found in properties file!");
        return propertyValue;
    }
}
