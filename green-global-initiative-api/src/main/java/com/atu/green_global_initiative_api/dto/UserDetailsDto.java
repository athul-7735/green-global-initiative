package com.atu.green_global_initiative_api.dto;

import com.atu.green_global_initiative_api.model.dao.ApplicationDetails;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Data Transfer Object (DTO) for User Details.
 * This class is used to transfer user-related information between layers of the application.
 */

@Data
@Getter
@Setter
public class UserDetailsDto {
    /**
     * Unique identifier for the user.
     */
    private int userId;
    /**
     * First name of the user.
     */
    private String firstName;
    /**
     * Last name of the user.
     */
    private String lastName;
    /**
     * Email address of the user.
     */
    private String email;
    /**
     * Indicates whether the user has admin privileges.
     */
    private boolean isAdmin;
    /**
     * The last login timestamp for the user.
     */
    private String lastLogin;
    /**
     * A list of application details associated with the user.
     */
    List<ApplicationDetails> applicationDetails;
    /**
     * Gets the unique identifier for the user.
     *
     * @return The user’s unique ID.
     */
    public int getUserId() {
        return userId;
    }
    /**
     * Sets the unique identifier for the user.
     *
     * @param userId The unique ID of the user.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }
    /**
     * Gets the first name of the user.
     *
     * @return The first name of the user.
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * Sets the first name of the user.
     *
     * @param firstName The first name of the user.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the user.
     *
     * @return The last name of the user.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the user.
     *
     * @param lastName The last name of the user.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the email address of the user.
     *
     * @return The email address of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address for the user.
     *
     * @param email The email address of the user.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets whether the user has admin privileges.
     *
     * @return True if the user is an admin, otherwise false.
     */
    public boolean getIsAdmin() {
        return isAdmin;
    }

    /**
     * Sets the admin status of the user.
     *
     * @param admin The admin status (true for admin, false otherwise).
     */
    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    /**
     * Gets the last login timestamp for the user.
     *
     * @return The last login timestamp for the user.
     */
    public String getLastLogin() {
        return lastLogin;
    }

    /**
     * Sets the last login timestamp for the user.
     *
     * @param lastLogin The last login timestamp of the user.
     */
    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    /**
     * Gets the list of application details associated with the user.
     *
     * @return A list of {@link ApplicationDetails} associated with the user.
     */
    public List<ApplicationDetails> getApplicationDetails() {
        return applicationDetails;
    }

    /**
     * Sets the application details associated with the user.
     *
     * @param applicationDetails A list of {@link ApplicationDetails} to set for the user.
     */
    public void setApplicationDetails(List<ApplicationDetails> applicationDetails) {
        this.applicationDetails = applicationDetails;
    }
}
