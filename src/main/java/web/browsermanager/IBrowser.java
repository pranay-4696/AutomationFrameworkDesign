package web.browsermanager;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Playwright;

public interface IBrowser {

    BrowserContext createBrowserSession(Playwright playwright, boolean isHeadless);
}
