package com.atu.green_global_initiative_api.model.dao;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * This class represents the entity for the application details in the Green Global Initiative API.
 * It maps to the "application_details" table in the database and contains information about the application,
 * including the organization, application status, funding request, project description, and admin comments.
 */
@Entity
@Data
@Getter
@Setter
@Table(name="application_details")
public class ApplicationDetails {
    /**
     * The unique identifier for the application.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="application_id", unique = true, nullable = false)
    private int applicationId;
    /**
     * The name of the organization submitting the application.
     */
    @Column(name="organization_name", nullable = false)
    private String organizationName;
    /**
     * The current status of the application (e.g., submitted, approved, rejected).
     */
    @Column(name="application_status", nullable = false)
    private String applicationStatus;
    /**
     * The approval date for the application, if applicable.
     */
    @Column(name="approval_date")
    private String approvalDate;
    /**
     * The amount of funding requested in the application.
     */
    @Column(name="requested_amount", nullable = false)
    private String requestedAmount;
    /**
     * A detailed description of the project for which the application is submitted.
     */
    @Column(name="project_description", nullable = false)
    private String projectDescription;
    /**
     * Admin comments or feedback regarding the application.
     */
    @Column(name="admin_comments", nullable = true)
    private String adminComments;
    /**
     * Gets the amount of funding requested in the application.
     *
     * @return the requested amount
     */
    public String getRequestedAmount() {
        return requestedAmount;
    }
    /**
     * Sets the amount of funding requested in the application.
     *
     * @param requestedAmount the new requested amount
     */
    public void setRequestedAmount(String requestedAmount) {
        this.requestedAmount = requestedAmount;
    }
    /**
     * The user details associated with the application.
     */
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserDetails userDetails;
    /**
     * The grant details associated with the application.
     */
    @ManyToOne
    @JoinColumn(name = "grant_id", referencedColumnName = "grant_id")
    private Grants grants;
    /**
     * Gets the unique identifier for the application.
     *
     * @return the application ID
     */
    public int getApplicationId() {
        return applicationId;
    }
    /**
     * Sets the unique identifier for the application.
     *
     * @param applicationId the new application ID
     */
    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }
    /**
     * Gets the name of the organization submitting the application.
     *
     * @return the organization name
     */
    public String getOrganizationName() {
        return organizationName;
    }
    /**
     * Sets the name of the organization submitting the application.
     *
     * @param organizationName the new organization name
     */
    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }
    /**
     * Gets the current status of the application.
     *
     * @return the application status
     */
    public String getApplicationStatus() {
        return applicationStatus;
    }
    /**
     * Sets the current status of the application.
     *
     * @param applicationStatus the new application status
     */
    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }
    /**
     * Gets the user details associated with the application.
     *
     * @return the user details
     */
    public UserDetails getUserDetails() {
        return userDetails;
    }
    /**
     * Sets the user details associated with the application.
     *
     * @param userDetails the new user details
     */
    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }
    /**
     * Gets the grant details associated with the application.
     *
     * @return the grant details
     */
    public Grants getGrants() {
        return grants;
    }
    /**
     * Sets the grant details associated with the application.
     *
     * @param grants the new grant details
     */
    public void setGrants(Grants grants) {
        this.grants = grants;
    }

    /**
     * Gets the approval date for the application.
     *
     * @return the approval date
     */
    public String getApprovalDate() {
        return approvalDate;
    }
    /**
     * Sets the approval date for the application.
     *
     * @param approvalDate the new approval date
     */
    public void setApprovalDate(String approvalDate) {
        this.approvalDate = approvalDate;
    }
    /**
     * Gets a detailed description of the project for which the application is submitted.
     *
     * @return the project description
     */
    public String getProjectDescription() {
        return projectDescription;
    }
    /**
     * Sets a detailed description of the project for which the application is submitted.
     *
     * @param projectDescription the new project description
     */
    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }
    /**
     * Gets admin comments or feedback regarding the application.
     *
     * @return the admin comments
     */
    public String getAdminComments() {
        return adminComments;
    }
    /**
     * Sets admin comments or feedback regarding the application.
     *
     * @param adminComments the new admin comments
     */
    public void setAdminComments(String adminComments) {
        this.adminComments = adminComments;
    }
}