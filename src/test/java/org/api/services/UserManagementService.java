package org.api.services;

import io.restassured.response.Response;
import org.api.models.request.ProfileUpdate;

/**
 * Service class for managing user profiles.
 */
public class UserManagementService extends BaseService {

    private static final String PROFILE_PATH = "/api/users/profile";

    /**
     * Retrieves the user profile using the provided token.
     *
     * @param token Authentication token.
     * @return Response containing the profile data.
     */
    public Response getProfile(String token) {
        setAuthToken(token);
        return getRequest(PROFILE_PATH);
    }

    /**
     * Updates the user profile with the given payload and token.
     *
     * @param payload Profile update data.
     * @param token   Authentication token.
     * @return Response after updating the profile.
     */
    public Response updateProfile(ProfileUpdate payload, String token) {
        setAuthToken(token);
        return putRequest(payload, PROFILE_PATH);
    }
}