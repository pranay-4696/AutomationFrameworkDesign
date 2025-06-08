package api.testcases.reqres;

import api.projects.reqres.models.UserDetailsResponse;
import api.projects.reqres.services.ReqResApiHelper;
import api.testcases.ApiBaseTest;
import commons.TestGroups;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Log4j2
public class ReqResTest extends ApiBaseTest {

    @Test(description = "Test : Get User By Id Api", dataProvider = "user-by-id-data", groups = {TestGroups.REGRESSION, TestGroups.SANITY})
    public void testGetUserById(String userId, String expectedEmail) {
        UserDetailsResponse userDetails = ReqResApiHelper.getReqResApiController().getUserById(userId);
        Assert.assertNotNull(userDetails, "User details should not be null for user ID: " + userId);
        UserDetailsResponse.Data userData = userDetails.getData();
        Assert.assertNotNull(userData, "User data should not be null for user ID: " + userId);
        Assert.assertEquals(userData.getEmail(), expectedEmail, "Email does not match for user ID: " + userId);
        log.info("Validated the User details for ID {}", userId);
    }

    @Test(description = "Test : Get User By Id Api with invalid user ID", dataProvider = "invalid-user-id-data", groups = {TestGroups.SANITY})
    public void testGetUserByInvalidId(String invalidUserId) {
        UserDetailsResponse userDetails = ReqResApiHelper.getReqResApiController().getInvalidUserById(invalidUserId);
        Assert.assertNull(userDetails.getData(), "User details should be null for invalid user ID: " + invalidUserId);
        log.info("Validated the Invalid User details for ID {}", invalidUserId);
    }

    @DataProvider(name = "user-by-id-data")
    public Object[][] getUserByIdTestData() {
        return new Object[][] {
                {"2", "janet.weaver@reqres.in"},
                {"3", "emma.wong@reqres.in"}
        };
    }

    @DataProvider(name = "invalid-user-id-data")
    public Object[][] getInvalidUserByIdTestData() {
        return new Object[][] {
                {"23"},
                {"30"}
        };
    }
}