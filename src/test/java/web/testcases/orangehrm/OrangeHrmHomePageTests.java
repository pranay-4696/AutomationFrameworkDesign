package web.testcases.orangehrm;

import api.lib.logging.CustomLogger;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.Test;
import web.testdata.OrangeHrmDataProviders;

import java.util.List;

@Log4j2
public class OrangeHrmHomePageTests extends OrangeHrmBaseTest {

    @Test(description = "Verify that the user can log in to OrangeHRM", dataProviderClass = OrangeHrmDataProviders.class,
            dataProvider = "home-page-action-tabs")
    public void testHomePage(List<String> expectedActionTabs) {
        List<String> actualActionTabs = ORANGE_HRM_PORTAL.get().ORANGE_HRM_HOME_PAGE.getActionTabs();
        Assert.assertEquals(actualActionTabs, expectedActionTabs,
                "Home Page Action Tabs Mismatched!");
        CustomLogger.info("Home Page Action Tabs Verified Successfully: {}", actualActionTabs);
    }

    @Test(description = "Verify the url for Dashboard Page")
    public void testDashboardUrl() {
        String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
        String actualUrl = ORANGE_HRM_PORTAL.get().ORANGE_HRM_HOME_PAGE.getUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "Url is not matched!");
        CustomLogger.info("Dashboard Page url Verified Successfully: {}", actualUrl);
    }
}
