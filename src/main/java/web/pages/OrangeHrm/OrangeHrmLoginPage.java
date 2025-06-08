package web.pages.OrangeHrm;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import web.commons.WebBasePage;

public class OrangeHrmLoginPage extends WebBasePage {

    public OrangeHrmLoginPage(Page webBasePage) {
        super(webBasePage);
    }

    public void enterUsername(String username) {
        String userNameSelector = "input[placeholder='Username']";
        waitForElement(userNameSelector);
        Locator userNameLocator = locators.getPageLocator(userNameSelector);
        fillText(userNameLocator, username);
    }

    public void enterPassword(String password) {
        String passwordSelector = "input[placeholder='Password']";
        waitForElement(passwordSelector);
        Locator userNameLocator = locators.getPageLocator(passwordSelector);
        fillText(userNameLocator, password);
    }

    public void clickLoginButton() {
        Locator locator = locators.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login"));
        clickElement(locator, "Login button Not Clicked!");
    }

    public boolean isOrangeHrmLoginSuccess(String emailId, String password) {
        enterUsername(emailId);
        enterPassword(password);
        clickLoginButton();
        return true;
    }
}
