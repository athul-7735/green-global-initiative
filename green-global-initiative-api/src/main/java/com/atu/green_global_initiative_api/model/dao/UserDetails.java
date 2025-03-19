package com.atu.green_global_initiative_api.model.dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
/**
 * Represents the user details in the Green Global Initiative application system.
 * This class contains the user's personal information, credentials, admin status,
 * last login details, and their associated application details.
 */
@Entity
@Data
@Getter
@Setter
@Table(name="user_details")
public class UserDetails {
    /**
     * Unique identifier for the user.
     * This ID is automatically generated.
     */
    @Id
    @JsonProperty("user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id", unique = true, nullable = false)
    private int userId;

    /**
     * First name of the user.
     */
    @JsonProperty("first_name")
    @Column(name="first_name",nullable = false)
    private String firstName;

    /**
     * Last name of the user.
     */
    @JsonProperty("last_name")
    @Column(name="last_name",nullable = false)
    private String lastName;

    /**
     * Email address of the user.
     */
    @JsonProperty("email")
    @Column(name="email",nullable = false)
    private String email;

    /**
     * Password for the user's account.
     * This field should be stored securely.
     */
    @JsonProperty("password")
    @Column(name="password",nullable = false)
    private String password;


    /**
     * Indicates whether the user has admin privileges.
     * True if the user is an admin, otherwise false.
     */
    @JsonProperty("isAdmin")

    @JsonProperty("is_admin")

    @Column(name="isAdmin",nullable = false)
    private boolean isAdmin;

    /**
     * The last login timestamp for the user.
     */
    @JsonProperty("last_login")
    @Column(name="last_login",nullable = false)
    private String lastLogin;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userDetails")
//    private List<ApplicationDetails> applicationDetailsList;
    /**
     * List of application details associated with this user.
     * The relationship is mapped by the "userDetails" field in ApplicationDetails.
     */
    @OneToMany(mappedBy = "userDetails", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ApplicationDetails> applicationDetails;

    /**
     * Gets the unique identifier for the user.
     *
     * @return the user ID
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the unique identifier for the user.
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
     * Gets the password of the user.
     *
     * @return the password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password for the user's account.
     *
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets whether the user has admin privileges.
     *
     * @return true if the user is an admin, otherwise false
     */
    public boolean isAdmin() {
        return isAdmin;
    }

    /**
     * Sets whether the user has admin privileges.
     *
     * @param admin true if the user is an admin, otherwise false
     */
    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
    /**
     * Gets the last login timestamp of the user.
     *
     * @return the last login timestamp of the user
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
     * Gets the list of application details associated with this user.
     *
     * @return a list of application details
     */
    public List<ApplicationDetails> getApplicationDetails() {
        return applicationDetails;
    }
    /**
     * Sets the list of application details associated with this user.
     *
     * @param applicationDetails the list of application details to set
     */
    public void setApplicationDetails(List<ApplicationDetails> applicationDetails) {
        this.applicationDetails = applicationDetails;
    }
}
