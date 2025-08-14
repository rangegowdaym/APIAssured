package org.api.services;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.api.filters.LoggerFilter;

/**
 * BaseService class provides utility methods for API interactions.
 * It includes methods to send GET, POST, and PUT requests, as well as support for setting authorization tokens.
 */
public class BaseService {

    private static final String BASE_URI = "http://64.227.160.186:8080";
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    protected final RequestSpecification requestSpecification;

    static {
        RestAssured.filters(new LoggerFilter());
    }

    /**
     * Initializes the BaseService with a pre-configured RequestSpecification.
     */
    public BaseService() {
        this.requestSpecification = RestAssured.given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON);
    }

    /**
     * Sends a POST request to the specified endpoint with the provided payload.
     *
     * @param payload  The request body payload.
     * @param endPoint The API endpoint to send the POST request to.
     * @return The Response object containing the API response.
     */
    protected Response postRequest(Object payload, String endPoint) {
        return requestSpecification
                .body(payload)
                .post(endPoint);
    }

    /**
     * Sends a GET request to the specified endpoint.
     *
     * @param endPoint The API endpoint to send the GET request to.
     * @return The Response object containing the API response.
     */
    protected Response getRequest(String endPoint) {
        return requestSpecification
                .get(endPoint);
    }

    /**
     * Sets the authorization token for the request.
     *
     * @param token The authorization token to be added as a header.
     * @return The updated RequestSpecification object with the Authorization header set.
     */
    protected RequestSpecification setAuthToken(String token) {
        return requestSpecification
                .header(AUTHORIZATION_HEADER, BEARER_PREFIX + token);
    }

    /**
     * Sends a PUT request to the specified endpoint with the provided payload.
     *
     * @param payload  The request body payload.
     * @param endPoint The API endpoint to send the PUT request to.
     * @return The Response object containing the API response.
     */
    protected Response putRequest(Object payload, String endPoint) {
        return requestSpecification
                .contentType(ContentType.JSON)
                .body(payload)
                .put(endPoint);
    }
}