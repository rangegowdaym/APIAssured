package org.api.tests;

import io.restassured.response.Response;
import org.api.models.request.LoginRequest;
import org.api.models.request.ProfileUpdate;
import org.api.models.response.LoginResponse;
import org.api.models.response.UserProfileResponse;
import org.api.services.AuthService;
import org.api.services.UserManagementService;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test class for user management functionality.
 * Contains tests for retrieving and updating user profiles.
 */
public class UserManagementTest {

    /**
     * Verifies that the user profile can be retrieved successfully.
     * Asserts that the username in the response matches the expected value.
     */
    @Test
    public void getProfileTest() {
        String token = loginAndGetToken();
        UserManagementService userManagementService = new UserManagementService();
        UserProfileResponse userProfileResponse = userManagementService
                .getProfile(token)
                .as(UserProfileResponse.class);

        Assert.assertEquals(userProfileResponse.getUsername(), "ranga.gowda");
    }

    /**
     * Verifies that the user profile can be updated successfully.
     * Prints the response after updating the profile.
     */
    @Test
    public void updateProfileTest() {
        String token = loginAndGetToken();
        UserManagementService userManagementService = new UserManagementService();

        ProfileUpdate profileUpdate = new ProfileUpdate.Builder()
                .mobileNumber("9844232432")
                .build();
        Response response = userManagementService.updateProfile(profileUpdate, token);

        System.out.println(response.asPrettyString());
    }

    /**
     * Logs in with valid credentials and retrieves the authentication token.
     *
     * @return Authentication token as a String.
     */
    private String loginAndGetToken() {
        AuthService authService = new AuthService();
        LoginRequest loginRequest = new LoginRequest.Builder()
                .username("ranga.gowda")
                .password("Gowda@890131")
                .build();
        Response response = authService.login(loginRequest);
        return response.as(LoginResponse.class).getToken();
    }

    /**
     * Verifies that retrieving a profile with an invalid token returns a 401 Unauthorized status.
     */
    @Test
    public void getProfileWithInvalidTokenShouldReturnUnauthorized() {
        UserManagementService userManagementService = new UserManagementService();
        Response response = userManagementService.getProfile("invalid_token");

        Assert.assertEquals(response.getStatusCode(), 401);
    }

    /**
     * Verifies that updating a profile with an invalid token returns a 401 Unauthorized status.
     */
    @Test
    public void updateProfileWithInvalidTokenShouldReturnUnauthorized() {
        UserManagementService userManagementService = new UserManagementService();
        ProfileUpdate profileUpdate = new ProfileUpdate.Builder()
                .mobileNumber("1234567890")
                .build();
        Response response = userManagementService.updateProfile(profileUpdate, "invalid_token");

        Assert.assertEquals(response.getStatusCode(), 401);
    }

    /**
     * Verifies that updating a profile with an empty payload returns a 400 Bad Request status.
     */
    @Test
    public void updateProfileWithEmptyPayloadShouldReturnBadRequest() {
        String token = loginAndGetToken();
        UserManagementService userManagementService = new UserManagementService();
        ProfileUpdate profileUpdate = new ProfileUpdate.Builder().build();
        Response response = userManagementService.updateProfile(profileUpdate, token);

        Assert.assertEquals(response.getStatusCode(), 400);
    }

    /**
     * Verifies that retrieving a profile with an expired token returns a 401 Unauthorized status.
     */
    @Test
    public void getProfileWithExpiredTokenShouldReturnUnauthorized() {
        UserManagementService userManagementService = new UserManagementService();
        Response response = userManagementService.getProfile("expired_token");

        Assert.assertEquals(response.getStatusCode(), 401);
    }
}