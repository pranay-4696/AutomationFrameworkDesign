package web.pages.OrangeHrm;

import com.microsoft.playwright.Page;
import web.commons.WebBasePage;

import java.util.List;

public class OrangeHrmHomePage extends WebBasePage {
    public OrangeHrmHomePage(Page webBasePage) {
        super(webBasePage);
    }

    public List<String> getActionTabs() {
        String actionTabsSelector = ".oxd-main-menu-item-wrapper";
        waitForElement(actionTabsSelector);
        return locators.getPageLocator(actionTabsSelector).allInnerTexts();
    }

    public String getUrl() {
        waitForElement("header");
        return webBasePage.url();
    }
}
