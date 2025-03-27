package com.atu.green_global_initiative_api.dto;

import com.atu.green_global_initiative_api.model.dao.ApplicationDetails;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

/**
 * Data Transfer Object (DTO) for holding user details in the Green Global Initiative API.
 */
@Data
@Getter
@Setter
public class UserDetailsDto {

    /** User ID */
    private int userId;

    /** First name of the user */
    private String firstName;

    /** Last name of the user */
    private String lastName;

    /** Email address of the user */
    private String email;

    /** Boolean indicating if the user is an admin */
    private boolean isAdmin;

    /** Last login timestamp of the user */
    private String lastLogin;

    /** List of application details associated with the user */
    List<ApplicationDetails> applicationDetails;

    /**
     * Gets the user ID.
     *
     * @return the user ID
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the user ID.
     *
     * @param userId the user ID to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the first name of the user.
     *
     * @return the first name of the user
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the user.
     *
     * @param firstName the first name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the user.
     *
     * @return the last name of the user
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the user.
     *
     * @param lastName the last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

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
     * Gets the admin status of the user.
     *
     * @return true if the user is an admin, false otherwise
     */
    public boolean getIsAdmin() {
        return isAdmin;
    }

    /**
     * Sets the admin status of the user.
     *
     * @param admin the admin status to set
     */
    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    /**
     * Gets the last login timestamp of the user.
     *
     * @return the last login timestamp
     */
    public String getLastLogin() {
        return lastLogin;
    }

    /**
     * Sets the last login timestamp of the user.
     *
     * @param lastLogin the last login timestamp to set
     */
    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    /**
     * Gets the list of application details associated with the user.
     *
     * @return the list of application details
     */
    public List<ApplicationDetails> getApplicationDetails() {
        return applicationDetails;
    }

    /**
     * Sets the list of application details associated with the user.
     *
     * @param applicationDetails the list of application details to set
     */
    public void setApplicationDetails(List<ApplicationDetails> applicationDetails) {
        this.applicationDetails = applicationDetails;
    }
}
