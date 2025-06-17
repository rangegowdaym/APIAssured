package org.api.models.request;

public class SignUpRequest {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String mobileNumber;

    // Default constructor
    public SignUpRequest() {
    }

    // Parameterized constructor
    public SignUpRequest(SignUpBuilder signUpBuilder) {
        this.username = signUpBuilder.username;
        this.password = signUpBuilder.password;
        this.email = signUpBuilder.email;
        this.firstName = signUpBuilder.firstName;
        this.lastName = signUpBuilder.lastName;
        this.mobileNumber = signUpBuilder.mobileNumber;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    @Override
    public String toString() {
        return "SignUpRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                '}';
    }

    public static class SignUpBuilder {
        private String username;
        private String password;
        private String email;
        private String firstName;
        private String lastName;
        private String mobileNumber;

        // Builder methods for setting properties
        public SignUpBuilder setUsername(String username) {
            this.username = username;
            return this;
        }

        public SignUpBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public SignUpBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public SignUpBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public SignUpBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public SignUpBuilder setMobileNumber(String mobileNumber) {
            this.mobileNumber = mobileNumber;
            return this;
        }

        // Build method to return a new User instance
        public SignUpRequest build() {
            return new SignUpRequest(this);
        }
    }
}

