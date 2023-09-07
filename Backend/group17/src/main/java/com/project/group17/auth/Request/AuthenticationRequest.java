package com.project.group17.auth.Request;

/**
 * This class represents an authentication request containing the user's email and password.
 * It is used to validate and authenticate a user's login attempt.
 */
public class AuthenticationRequest {
    private String email;
    private String password;

    /**
     * Constructs an AuthenticationRequest with the specified email and password.
     *
     * @param email The user's email address.
     * @param password The user's password.
     */
    public AuthenticationRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * Gets the email address associated with this authentication request.
     *
     * @return The user's email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address associated with this authentication request.
     *
     * @param email The user's email address to be set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the password associated with this authentication request.
     *
     * @return The user's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password associated with this authentication request.
     *
     * @param password The user's password to be set.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
