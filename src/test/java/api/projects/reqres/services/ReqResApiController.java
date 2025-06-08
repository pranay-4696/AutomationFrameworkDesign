package api.projects.reqres.services;

import api.constants.HttpStatuses;
import api.lib.assertions.AssertionHelper;
import api.projects.reqres.models.UserDetailsResponse;
import api.utils.JsonHelper;
import io.restassured.response.Response;

public class ReqResApiController {

    private final ReqResApiClient reqResApiClient = new ReqResApiClient();
    private final JsonHelper jsonHelper = new JsonHelper();
    private final AssertionHelper assertionHelper = new AssertionHelper();

    public UserDetailsResponse getUserById(String userId) {
        Response response = reqResApiClient.getUserById(userId);
        assertionHelper.validateStatusCode(response, HttpStatuses.OK.getCode(), "Failed to get user details for ID: " + userId);
        return jsonHelper.getObjectFromString(response.body().asString(), UserDetailsResponse.class);
    }

    public UserDetailsResponse getInvalidUserById(String userId) {
        Response response = reqResApiClient.getUserById(userId);
        assertionHelper.validateStatusCode(response, HttpStatuses.NOT_FOUND.getCode(), "Expected NOT FOUND status for invalid user ID: " + userId);
        return jsonHelper.getObjectFromString(response.body().asString(), UserDetailsResponse.class);
    }
}