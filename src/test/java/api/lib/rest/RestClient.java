package api.lib.rest;

import api.lib.logging.CustomLogger;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;

import java.util.Map;

public class RestClient implements IRestClient {

    @Override
    public Response get(String serverURL, Header headerMap) {
        CustomLogger.info("GET request to URL: {}", serverURL);
        try {
            Response response = RestAssured.given()
                    .header(headerMap)
                    .when()
                    .get(serverURL)
                    .then()
                    .extract()
                    .response();
            CustomLogger.info("Response: {}", response.asString());
            return response;
        } catch (Exception e) {
            CustomLogger.error("Error during GET request to URL: {}", serverURL, e);
            throw new RuntimeException("GET request failed", e);
        }
    }

    @Override
    public Response get(String serverURL, Header headerMap, Map<String, Object> parameters) {
        CustomLogger.info("GET request to URL: {} with parameters: {}", serverURL, parameters);
        try {
            Response response = RestAssured.given()
                    .header(headerMap)
                    .queryParams(parameters)
                    .when()
                    .get(serverURL)
                    .then()
                    .extract()
                    .response();
            CustomLogger.info("Response : {}", response.asString());
            return response;
        } catch (Exception e) {
            CustomLogger.error("Error during GET request to URL: {} with parameters: {}", serverURL, parameters, e);
            throw new RuntimeException("GET request with parameters failed", e);
        }
    }
}