package com.project.group17.auth.Response;

import lombok.Builder;

/**
 * This class represents an authentication response containing the user's authentication token.
 * It is used to provide an authenticated user with a JWT token after successful login.
 */
@Builder
public class AuthenticationResponse {
    private String token;

    /**
     * Constructs an AuthenticationResponse with the specified JWT token.
     *
     * @param token The JWT token associated with the authenticated user.
     */
    public AuthenticationResponse(String token) {
        this.token = token;
    }

    /**
     * Gets the JWT token associated with this authentication response.
     *
     * @return The JWT token.
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets the JWT token associated with this authentication response.
     *
     * @param token The JWT token to be set.
     */
    public void setToken(String token) {
        this.token = token;
    }
}
