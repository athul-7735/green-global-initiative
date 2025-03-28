package com.atu.green_global_initiative_api.model.dao;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity class representing the application details in the Green Global Initiative database.
 * This class is mapped to the "application_details" table and contains details of a specific grant application.
 */
@Entity
@Data
@Getter
@Setter
@Table(name="application_details")
public class ApplicationDetails {

    /** Unique identifier for the application */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="application_id", unique = true, nullable = false)
    private int applicationId;

    /** Name of the organization applying for the grant */
    @Column(name="organization_name", nullable = false)
    private String organizationName;

    /** Status of the application (e.g., approved, pending, rejected) */
    @Column(name="application_status", nullable = false)
    private String applicationStatus;

    /** Date when the application was approved */
    @Column(name="approval_date")
    private String approvalDate;

    /** Requested amount for the grant */
    @Column(name="requested_amount", nullable = false)
    private String requestedAmount;

    /** Description of the project or initiative for which the grant is requested */
    @Column(name="project_description", nullable = false)
    private String projectDescription;

    /** Comments from the admin regarding the application */
    @Column(name="admin_comments", nullable = true)
    private String adminComments;

    @Column(name="special_award", nullable = false)
    private boolean specialAward;

    public String getRequestedAmount() {
        return requestedAmount;
    }

    public void setRequestedAmount(String requestedAmount) {
        this.requestedAmount = requestedAmount;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserDetails userDetails;

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
     * @param applicationId the application ID to set
     */
    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    /**
     * Gets the name of the organization applying for the grant.
     *
     * @return the name of the organization
     */
    public String getOrganizationName() {
        return organizationName;
    }


    /**
     * Sets the name of the organization applying for the grant.
     *
     * @param organizationName the organization name to set
     */
    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    /**
     * Gets the status of the application.
     *
     * @return the status of the application
     */
    public String getApplicationStatus() {
        return applicationStatus;
    }

    /**
     * Sets the status of the application.
     *
     * @param applicationStatus the application status to set
     */
    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    /**
     * Gets the user details associated with this application.
     *
     * @return the user details
     */
    public UserDetails getUserDetails() {
        return userDetails;
    }

    /**
     * Sets the user details associated with this application.
     *
     * @param userDetails the user details to set
     */
    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    /**
     * Gets the grant details associated with this application.
     *
     * @return the grant details
     */
    public Grants getGrants() {
        return grants;
    }

    /**
     * Sets the grant details associated with this application.
     *
     * @param grants the grant details to set
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
     * @param approvalDate the approval date to set
     */
    public void setApprovalDate(String approvalDate) {
        this.approvalDate = approvalDate;
    }

    /**
     * Gets the description of the project or initiative.
     *
     * @return the project description
     */
    public String getProjectDescription() {
        return projectDescription;
    }

    /**
     * Sets the description of the project or initiative.
     *
     * @param projectDescription the project description to set
     */
    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    /**
     * Gets the admin comments related to the application.
     *
     * @return the admin comments
     */
    public String getAdminComments() {
        return adminComments;
    }

    /**
     * Sets the admin comments related to the application.
     *
     * @param adminComments the admin comments to set
     */
    public void setAdminComments(String adminComments) {
        this.adminComments = adminComments;
    }

    public boolean getSpecialAward() {
        return specialAward;
    }

    public void setSpecialAward(boolean specialAward) {
        this.specialAward = specialAward;
    }
}