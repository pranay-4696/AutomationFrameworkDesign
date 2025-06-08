package api.lib.rest;

import io.restassured.http.Header;
import io.restassured.response.Response;

import java.util.Map;

public interface IRestClient {

    /**
     * Sends a GET request to the given URL with a single header.
     *
     * @param url The URL to send the GET request to.
     * @param header The header to include in the request.
     * @return The response object.
     */
    Response get(String url, Header header);

    /**
     * Sends a GET request to the given URL with a single header and query parameters.
     *
     * @param url The URL to send the GET request to.
     * @param header The header to include in the request.
     * @param parameters Query parameters as key-value pairs.
     * @return The response object.
     */
    Response get(String url, Header header, Map<String, Object> parameters);
}