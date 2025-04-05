package web.commons;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import web.browsermanager.BrowserManager;
import web.listeners.WebPortalTestListeners;

import java.lang.reflect.Method;

@Log4j2
public abstract class WebBaseTest {

    protected static ThreadLocal<Page> page = new ThreadLocal<>();
    protected static ThreadLocal<Playwright> playwright = new ThreadLocal<>();
    protected static ThreadLocal<Browser> browser = new ThreadLocal<>();
    private static BrowserManager browserManager;

    @BeforeSuite(alwaysRun = true)
    public void setUp() {
        browserManager = new BrowserManager();
        log.info("Browser Manager has been initiated.");
    }

    @BeforeMethod(alwaysRun = true)
    public void init(Method method) {
        playwright.set(Playwright.create());
        page.set(browserManager.getBrowserPage(playwright.get()));
        browser.set(page.get().context().browser());
        log.info("Browser has been set.");
        WebPortalTestListeners.setPage(page.get());
    }

    @AfterMethod(alwaysRun = true)
    public void destroy() {
        browserManager.destroyBrowserPage(page.get(), browser.get());
        playwright.get().close();
        playwright.remove();
        log.info("Browser has been destroyed.");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        if (playwright.get() != null) {
            playwright.get().close();
            playwright.remove();
            log.info("Playwright has been closed.");
        }
        log.info("Enabling OTP in LSG environment started...");
    }
}
