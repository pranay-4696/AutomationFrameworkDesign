package web.browsermanager;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import web.constants.WebConstants;
import lombok.extern.log4j.Log4j2;

import java.util.Objects;

@Log4j2
public class BrowserManager implements IBrowserManager<Page> {

    @Override
    public Page getBrowserPage(Playwright playwright) {
        boolean isHeadless = WebConstants.RUN_MODE.equalsIgnoreCase(WebConstants.HEADLESS);
        return new BrowserFactory().createBrowser(WebConstants.BROWSER_NAME)
                .createBrowserSession(playwright, isHeadless).newPage();
    }

    @Override
    public void destroyBrowserPage(Page page, Browser browser) {
        Objects.requireNonNull(page, "Playwright Browser is null!");
        page.close();
        Objects.requireNonNull(browser, "Playwright Browser is null!");
        browser.close();
    }
}