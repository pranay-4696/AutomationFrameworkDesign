package web.locators;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import java.util.Objects;

public class Locators implements ILocators<Locator> {

    private final Page PAGE;

    public Locators(Page page) {
        this.PAGE = page;
    }

    @Override
    public Locator getPageLocator(String webLocatorText) {
        Objects.requireNonNull(webLocatorText, "Web Locator Text cannot be null or empty!");
        return PAGE.locator(webLocatorText);
    }

    @Override
    public Locator getByText(String webLocatorText) {
        Objects.requireNonNull(webLocatorText, "Web Locator Text cannot be null or empty!");
        return PAGE.getByText(webLocatorText);
    }

    @Override
    public Locator getByRole(AriaRole role, Page.GetByRoleOptions options) {
        Objects.requireNonNull(role, "Role cannot be null");
        Objects.requireNonNull(options, "Options cannot be null");
        return PAGE.getByRole(role, options);
    }

    @Override
    public Locator getByLabel(String labelText) {
        Objects.requireNonNull(labelText, "Label Text cannot be null or empty!");
        return PAGE.getByLabel(labelText);
    }

    @Override
    public Locator getByPlaceholder(String placeholderText) {
        Objects.requireNonNull(placeholderText, "Placeholder Text cannot be null!");
        return PAGE.getByPlaceholder(placeholderText);
    }
}