package web.listeners;

import com.microsoft.playwright.Page;
import web.constants.WebConstants;
import lombok.extern.log4j.Log4j2;
import org.testng.*;

import java.nio.file.Paths;
import java.util.Date;

@Log4j2
public class WebPortalTestListeners implements ITestListener, ISuiteListener, IRetryAnalyzer {

    private static final int MAX_RETRY = 1;
    private int retryCount = 0;

    // SCREENSHOT VARIABLES
    private static final String SCREENSHOTS_DIRECTORY = "./target/screenshots";
    private static final String PASS = "/passed_screenshots/";
    private static final String PASS_PREFIX = "PASS_";
    private static final String FAIL = "/failed_screenshots/";
    private static final String FAIL_PREFIX = "FAILED_";
    private static final String IMAGE_FORMAT = ".png";

    // ThreadLocal to store the Page object
    private static final ThreadLocal<Page> PAGE = new ThreadLocal<>();

    // Method to set the Page object
    public static void setPage(Page page) {
        WebPortalTestListeners.PAGE.set(page);
    }

    @Override
    public void onStart(ISuite suite) {
        log.info("Test suite execution started...");
        String browserName = WebConstants.BROWSER_NAME;
        String runMode = WebConstants.RUN_MODE;
        log.info("Browser: {} | Run Mode: {}", browserName, runMode);
    }

    @Override
    public void onFinish(ISuite suite) {
        log.info("Test suite execution completed.");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        takeScreenshot(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        takeScreenshot(result);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        takeScreenshot(result);
    }

    private void takeScreenshot(ITestResult testResult) {
        Page currentPage = PAGE.get();
        if (currentPage == null || currentPage.isClosed()) {
            log.error("Page object is null or already closed. Cannot take screenshot.");
            return;
        }
        String testName = testResult.getName();
        String statusPrefix = testResult.isSuccess() ? PASS_PREFIX : FAIL_PREFIX;
        String directory = testResult.isSuccess() ? PASS : FAIL;
        String parameters = (testResult.getParameters().length > 0)
                ? testResult.getParameters()[0].toString() : "NoParams";
        String filePath = String.format("%s%s%s_%s_%s%s_%s_%s%s", SCREENSHOTS_DIRECTORY,
                directory, WebConstants.BROWSER_NAME, WebConstants.RUN_MODE, statusPrefix,
                testName, parameters, new Date(), IMAGE_FORMAT);
        try {
            currentPage.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(filePath)).setFullPage(true));
        } catch (Exception e) {
            log.error("Failed to take screenshot for: {} due to: {}",
                    testName + "_" + parameters, e.getMessage());
        }
    }

    @Override
    public boolean retry(ITestResult result) {
        if (!result.isSuccess() && retryCount < MAX_RETRY) {
            log.error("Retrying test for {} time(s) for the test method {} with test status {}.",
                    retryCount + 1, result.getName(), getTestStatusName(result.getStatus()));
            retryCount++;
            return true;
        }
        log.error("Retrying for the test method {} is exhausted.", result.getName());
        return false;
    }

    private String getTestStatusName(int status) {
        switch (status) {
            case 1:
                return "SUCCESS";
            case 2:
                return "FAILED";
            case 3:
                return "SKIP";
            default:
                return null;
        }
    }
}
