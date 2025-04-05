package web.testcases.orangehrm;

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
    }
}
