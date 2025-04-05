package web.browsermanager;

import web.constants.BrowserName;
import web.exceptions.WebUtilsException;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class BrowserFactory {

    public IBrowser createBrowser(String browserName) {
        switch (BrowserName.fromString(browserName)) {
            case CHROME:
                return new ChromeBrowser();
            case MS_EDGE:
                return new MsEdgeBrowser();
            case FIREFOX:
                return new FirefoxBrowser();
            default:
                throw new WebUtilsException("Unknown browser: " + browserName);
        }
    }
}