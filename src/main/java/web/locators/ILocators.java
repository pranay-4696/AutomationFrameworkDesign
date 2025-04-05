package web.locators;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public interface ILocators<T> {

    T getPageLocator(String webLocator);

    T getByText(String webLocatorText);

    T getByRole(AriaRole role, Page.GetByRoleOptions options);

    T getByLabel(String labelText);

    T getByPlaceholder(String placeholderText);
}
