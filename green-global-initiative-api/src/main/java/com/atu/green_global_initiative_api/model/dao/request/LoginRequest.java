package com.atu.green_global_initiative_api.model.dao.request;

import lombok.Data;

/**
 * Request DTO for user login in the Green Global Initiative API.
 * This class contains the necessary fields for a user to log in by providing email and password.
 */
@Data
public class LoginRequest {

    /** Email address of the user */
    private String email;

    /** Password for the user's login */
    private String password;

    /**
     * Gets the email address of the user.
     *
     * @return the email address of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the user.
     *
     * @param email the email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the password for the user's login.
     *
     * @return the user's password
     */
    // Getter for password
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password for the user's login.
     *
     * @param password the password to set
     */
    // Setter for password
    public void setPassword(String password) {
        this.password = password;
    }
}
