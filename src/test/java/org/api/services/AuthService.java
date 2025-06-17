package org.api.services;

import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * AuthService class provides methods for authentication-related API actions,
 * such as login, signup, and password recovery.
 */
public class AuthService extends BaseService {

    private static final String BASE_PATH = "/api/auth/";
    private static final String LOGIN_ENDPOINT = "login";
    private static final String SIGNUP_ENDPOINT = "signup";
    private static final String FORGOT_PASSWORD_ENDPOINT = "forgot-password";

    /**
     * Sends a login request with the provided payload.
     *
     * @param payload The login request body containing user credentials.
     * @return The Response object containing the API response.
     */
    public Response login(Object payload) {
        return postRequest(payload, BASE_PATH + LOGIN_ENDPOINT);
    }

    /**
     * Sends a signup request with the provided payload.
     *
     * @param payload The signup request body containing user registration details.
     * @return The Response object containing the API response.
     */
    public Response signup(Object payload) {
        return postRequest(payload, BASE_PATH + SIGNUP_ENDPOINT);
    }

    /**
     * Sends a request to recover the password for the specified email address.
     *
     * @param emailAddress The email address for which password recovery is requested.
     * @return The Response object containing the API response.
     */
    public Response forgotPassword(String emailAddress) {
        Map<String, String> payload = new HashMap<>();
        payload.put("email", emailAddress);
        return postRequest(payload, BASE_PATH + FORGOT_PASSWORD_ENDPOINT);
    }
}