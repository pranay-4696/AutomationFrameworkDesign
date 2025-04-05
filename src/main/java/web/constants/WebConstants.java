package web.constants;

import web.utils.WebAppConfigLoader;

public class WebConstants {

    public static final WebAppConfigLoader CONFIG_LOADER = WebAppConfigLoader.getInstance();
    // Browser Configs
    public static final String BROWSER_NAME = CONFIG_LOADER.getBrowser();
    public static final String RUN_MODE = CONFIG_LOADER.getRunMode();
    public static final String MAXIMIZE_WINDOW = "--start-maximized";

    public static final String HEADLESS = "headless";

    //Orange HRM Login Credentials
    public static final String ORANGE_HRM_WEB_URL = CONFIG_LOADER.getOrangeHrmWebUrl();
    public static final String ORANGE_HRM_EMAIL_ID = CONFIG_LOADER.getOrangeHrmUsername();
    public static final String ORANGE_HRM_PASSWORD = CONFIG_LOADER.getOrangeHrmPassword();
}
