package web.testcases.orangehrm;

import lombok.extern.log4j.Log4j2;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import web.commons.WebBaseTest;
import web.exceptions.OrangeHrmWebException;
import web.modules.OrangeHrmPortal;

import java.lang.reflect.Method;
import web.constants.WebConstants;

@Log4j2
public class OrangeHrmBaseTest extends WebBaseTest {

    protected static final ThreadLocal<OrangeHrmPortal> ORANGE_HRM_PORTAL = new ThreadLocal<>();

    @BeforeMethod(alwaysRun = true)
    public void setUpOrangeHrmPortal(Method method) {
        log.info("Finance Center Thread id in BeforeMethod for the test method : {} is {}.", method.getName(), Thread.currentThread().getId());
        String emailId = WebConstants.ORANGE_HRM_EMAIL_ID;
        String password = WebConstants.ORANGE_HRM_PASSWORD;
        ORANGE_HRM_PORTAL.set(new OrangeHrmPortal(page.get()));
        ORANGE_HRM_PORTAL.get().visit();
        boolean isLoginSuccess = ORANGE_HRM_PORTAL.get().ORANGE_HRM_LOGIN_PAGE
                .isOrangeHrmLoginSuccess(emailId, password);
        if (!isLoginSuccess)
            throw new OrangeHrmWebException("Orange Hrm Portal Not Logged In!");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownOrangeHrmPortal(Method method) {
        log.info("Orange Hrm Portal Thread id in AfterMethod for the test method :{} is {}.", method.getName(), Thread.currentThread().getId());
        ORANGE_HRM_PORTAL.remove();
    }
}
