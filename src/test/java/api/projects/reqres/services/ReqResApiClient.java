package api.projects.reqres.services;

import api.lib.properties.PropertyFileReader;
import api.lib.rest.RestClient;
import api.projects.reqres.endpoints.ReqResEndpoints;
import io.restassured.http.Header;
import io.restassured.response.Response;

public class ReqResApiClient {

    private final RestClient restClient = new RestClient();

    public Response getUserById(String userId) {
        Header header = getAuthorizationHeader();
        String getUserByIdEndpoint = ReqResEndpoints.GET_USER_BY_ID
                .replace("{reqResServer}", PropertyFileReader.getPropertyValue("reqResServer"))
                .replace("{userId}", userId);
        return restClient.get(getUserByIdEndpoint, header);
    }

    public static Header getAuthorizationHeader() {
        String reqResAuthKey = PropertyFileReader.getPropertyValue("reqResAuthKey");
        String reqResAuthToken = PropertyFileReader.getPropertyValue("reqResAuthToken");
        return new Header(reqResAuthKey, reqResAuthToken);
    }
}