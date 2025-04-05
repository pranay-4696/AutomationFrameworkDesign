package web.modules;

import com.microsoft.playwright.Page;
import web.constants.WebConstants;
import web.pages.OrangeHrm.OrangeHrmHomePage;
import web.pages.OrangeHrm.OrangeHrmLoginPage;

public class OrangeHrmPortal  {

    private final Page PAGE;
    private final String ORANGE_HRM_URL;
    public final OrangeHrmLoginPage ORANGE_HRM_LOGIN_PAGE;
    public final OrangeHrmHomePage ORANGE_HRM_HOME_PAGE;

    public OrangeHrmPortal(Page page) {
        this.PAGE = page;
        this.ORANGE_HRM_URL = WebConstants.ORANGE_HRM_WEB_URL;
        this.ORANGE_HRM_LOGIN_PAGE = new OrangeHrmLoginPage(page);
        this.ORANGE_HRM_HOME_PAGE = new OrangeHrmHomePage(page);
    }

    public void visit() {
        PAGE.navigate(ORANGE_HRM_URL);
    }
}
