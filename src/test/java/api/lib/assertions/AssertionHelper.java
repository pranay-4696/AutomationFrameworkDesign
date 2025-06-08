package api.lib.assertions;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.util.Objects;

@Log4j2
public class AssertionHelper {

    private static final Logger LOG = LogManager.getLogger();

    public void validateStatusCode(Response response, int status, String... message) {
        Objects.requireNonNull(response, "Response cannot be null");
        validateStatusCode(response.statusCode(), status, message);
    }

    public void validateStatusCode(int actualStatus, int expectedStatus, String... message) {
        LOG.info("Actual Status {}", actualStatus);
        LOG.info("Expected Status {}", expectedStatus);
        String msg = "";
        if (message != null && message.length > 0) {
            msg = message[0];
        }
        Assert.assertEquals(actualStatus, expectedStatus, "API response code mismatch: " + msg);
    }

    public void validateResponseBodyType(Response response, ContentType contentType) {
        String actual = response.getHeader("Content-Type").toLowerCase();
        String expected = contentType.getContentTypeStrings()[0].toLowerCase();
        Assert.assertTrue(actual.contains(expected), "API response content type mismatch");
    }

    //Need to Implement SoftAssertions, Object, POJO and JSON validation methods
}