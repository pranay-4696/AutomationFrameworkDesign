package web.browsermanager;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import web.constants.WebConstants;

import java.util.Collections;

public class ChromeBrowser implements IBrowser {
    @Override
    public BrowserContext createBrowserSession(Playwright playwright, boolean isHeadless) {
        return playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(isHeadless)
                .setArgs(Collections.singletonList(WebConstants.MAXIMIZE_WINDOW))).newContext();
    }
}