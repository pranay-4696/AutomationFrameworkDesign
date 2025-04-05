package web.commons;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;
import web.exceptions.WebPageException;
import web.locators.Locators;

import java.util.Objects;

public class WebBasePage {

    private static final String ERROR_MESSAGE = "Error Message cannot be null or empty!";

    protected Page webBasePage;
    protected Locators locators;

    public WebBasePage(Page webBasePage) {
        this.webBasePage = webBasePage;
        this.locators = new Locators(webBasePage);
    }

    protected void waitForElement(String selector) {
        Objects.requireNonNull(selector, "Selector cannot be null or empty!");
        webBasePage.waitForSelector(selector,
                new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
    }

    protected void validateAction(boolean condition, String errorMessage) {
        Objects.requireNonNull(errorMessage, ERROR_MESSAGE);
        if (!condition)
            throw new WebPageException(errorMessage);
    }

    protected void validateNonEmptyText(String text, String errorMessage) {
        Objects.requireNonNull(errorMessage, ERROR_MESSAGE);
        if (text.isEmpty())
            throw new WebPageException(errorMessage);
    }

    protected void clickElement(Locator locator, String errorMessage) {
        Objects.requireNonNull(locator, "Fill Text Locator cannot be null or empty!");
        Objects.requireNonNull(errorMessage, ERROR_MESSAGE);
        validateLocator(locator, errorMessage);
        locator.click();
    }

    protected void fillText(Locator locator, String textContent) {
        Objects.requireNonNull(locator, "Fill Text Locator cannot be null or empty!");
        Objects.requireNonNull(textContent, "Fill Text content cannot be null or empty!");
        validateLocator(locator, "Text area is not visible or enabled!");
        locator.clear();
        locator.fill(textContent);
    }

    private void validateLocator(Locator locator, String errorMessage) {
        if (!locator.isVisible() || !locator.isEnabled())
            throw new WebPageException(errorMessage);
    }

    public boolean isCheckBoxChecked(String checkboxName) {
        validateNonEmptyText(checkboxName, "Checkbox Name is Empty");
        return locators.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName(checkboxName)).isChecked();
    }
}