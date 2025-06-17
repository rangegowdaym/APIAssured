package org.api.models.request;

/**
 * Represents a user profile update request.
 * Provides a builder for constructing instances.
 */
public class ProfileUpdate {

    private final String firstName;
    private final String lastName;
    private final String email;
    private final String mobileNumber;

    // Private constructor to enforce builder usage
    private ProfileUpdate(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.mobileNumber = builder.mobileNumber;
    }

    // Getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    /**
     * Builder class for constructing ProfileUpdate instances.
     */
    public static class Builder {
        private String firstName;
        private String lastName;
        private String email;
        private String mobileNumber;

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder mobileNumber(String mobileNumber) {
            this.mobileNumber = mobileNumber;
            return this;
        }

        public ProfileUpdate build() {
            return new ProfileUpdate(this);
        }
    }

    @Override
    public String toString() {
        return String.format("ProfileUpdate{firstName='%s', lastName='%s', email='%s', mobileNumber='%s'}",
                firstName, lastName, email, mobileNumber);
    }
}