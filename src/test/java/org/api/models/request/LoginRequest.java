package org.api.models.request;

/**
 * Represents a login request with username and password.
 * This class is immutable and uses a builder pattern for object creation.
 */
public class LoginRequest {
    private final String username; // The username for the login request
    private final String password; // The password for the login request

    /**
     * Private constructor to enforce the use of the Builder for object creation.
     *
     * @param builder The builder instance containing the username and password.
     */
    private LoginRequest(Builder builder) {
        this.username = builder.username;
        this.password = builder.password;
    }

    /**
     * Retrieves the username of the login request.
     *
     * @return The username as a String.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Retrieves the password of the login request.
     *
     * @return The password as a String.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Builder class for constructing instances of LoginRequest.
     */
    public static class Builder {
        private String username; // The username to be set in the LoginRequest
        private String password; // The password to be set in the LoginRequest

        /**
         * Sets the username for the LoginRequest.
         *
         * @param username The username as a String.
         * @return The Builder instance for method chaining.
         */
        public Builder username(String username) {
            this.username = username;
            return this;
        }

        /**
         * Sets the password for the LoginRequest.
         *
         * @param password The password as a String.
         * @return The Builder instance for method chaining.
         */
        public Builder password(String password) {
            this.password = password;
            return this;
        }

        /**
         * Builds and returns a new LoginRequest instance.
         *
         * @return A new LoginRequest object.
         */
        public LoginRequest build() {
            return new LoginRequest(this);
        }
    }

    /**
     * Returns a string representation of the LoginRequest object.
     *
     * @return A formatted string containing the username and password.
     */
    @Override
    public String toString() {
        return String.format("LoginRequest{username='%s', password='%s'}", username, password);
    }
}