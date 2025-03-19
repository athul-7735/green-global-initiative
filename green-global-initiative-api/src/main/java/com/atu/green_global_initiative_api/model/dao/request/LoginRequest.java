package com.atu.green_global_initiative_api.model.dao.request;

import lombok.Data;
/**
 * This class represents the request body for a login request in the Green Global Initiative API.
 * It contains the necessary information (email and password) for authenticating a user.
 */
@Data
public class LoginRequest {
    /**
     * The email address of the user attempting to log in.
     */
    private String email;
    /**
     * The password associated with the user's email for authentication.
     */
    private String password;
    /**
     * Gets the email address of the user attempting to log in.
     *
     * @return the email address
     */
    public String getEmail() {
        return email;
    }
    /**
     * Sets the email address of the user attempting to log in.
     *
     * @param email the new email address
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Gets the password associated with the user's email.
     *
     * @return the password
     */
    // Getter for password
    public String getPassword() {
        return password;
    }
    /**
     * Sets the password associated with the user's email.
     *
     * @param password the new password
     */
    // Setter for password
    public void setPassword(String password) {
        this.password = password;
    }
}
