package web.testdata;

import org.testng.annotations.DataProvider;

import java.util.Arrays;

public class OrangeHrmDataProviders {

    @DataProvider(name = "home-page-action-tabs")
    public Object[][] homePageActionTabs() {
        return new Object[][]{
                {Arrays.asList("Admin", "PIM", "My Info", "Dashboard")}
        };
    }
}
