package org.api.tests;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.api.listeners.TestListener;
import org.api.models.request.LoginRequest;
import org.api.models.request.ProfileUpdate;
import org.api.models.response.LoginResponse;
import org.api.models.response.UserProfileResponse;
import org.api.services.AuthService;
import org.api.services.UserManagementService;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * Test class for user management functionality.
 * Contains tests for retrieving and updating user profiles.
 */
@Listeners(TestListener.class)
@Epic("User Management")
@Feature("User Profile Service")
public class UserManagementTest {

    /**
     * Verifies that the user profile can be retrieved successfully.
     * Asserts that the username in the response matches the expected value.
     */
    @Test(description = "Verify the user profile retrieval functionality.")
    @Story("Get User Profile")
    @Description("This test case verifies that a user profile can be retrieved successfully using the User Management API.")
    @Severity(SeverityLevel.NORMAL)
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
    @Test(description = "Verify the user profile update functionality.")
    @Story("Update User Profile")
    @Description("This test case verifies that a user profile can be updated successfully using the User Management API.")
    @Severity(SeverityLevel.NORMAL)
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
    @Test(description = "Verify retrieving profile with invalid token should return Unauthorized")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Get User Profile with Invalid Token")
    @Description("This test case verifies that retrieving a user profile with an invalid token returns a 401 Unauthorized status.")
    public void getProfileWithInvalidTokenShouldReturnUnauthorized() {
        UserManagementService userManagementService = new UserManagementService();
        Response response = userManagementService.getProfile("invalid_token");

        Assert.assertEquals(response.getStatusCode(), 401);
    }

    /**
     * Verifies that updating a profile with an invalid token returns a 401 Unauthorized status.
     */
    @Test(description = "Verify updating profile with invalid token should return Unauthorized")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Update User Profile with Invalid Token")
    @Description("This test case verifies that updating a user profile with an invalid token returns a 401 Unauthorized status.")
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
    @Test(description = "Verify updating profile with empty payload should return Bad Request")
    @Severity(SeverityLevel.NORMAL)
    @Story("Update User Profile with Empty Payload")
    @Description("This test case verifies that updating a user profile with an empty payload returns a 400 Bad Request status.")
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
    @Test(description = "Verify retrieving profile with expired token should return Unauthorized")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Get User Profile with Expired Token")
    @Description("This test case verifies that retrieving a user profile with an expired token returns a 401 Unauthorized status.")
    public void getProfileWithExpiredTokenShouldReturnUnauthorized() {
        UserManagementService userManagementService = new UserManagementService();
        Response response = userManagementService.getProfile("expired_token");

        Assert.assertEquals(response.getStatusCode(), 401);
    }
}