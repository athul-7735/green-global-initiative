package com.atu.green_global_initiative_api.model.dao.request;

import lombok.Data;

/**
 * Request DTO for creating a new application in the Green Global Initiative API.
 * This class contains the necessary information required to create a new application.
 */
@Data
public class ApplicationCreateRequest {

    /** Application ID */
    private int applicationId;

    /** User ID of the person submitting the application */
    private int userId;

    /** Name of the organization applying for the grant */
    private String organizationName;

    /** Grant ID associated with the application */
    private int grantId;

    /** Status of the application */
    private String applicationStatus;

    /** Date when the application was approved */
    public String approvalDate;

    /** The requested amount for the grant */
    public String requestedAmount;

    /** Description of the project for which the grant is being requested */
    public String projectDescription;

    /** The special award for the grant */
    public boolean specialAward;

    /**
     * Gets the application ID.
     *
     * @return the application ID
     */
    public int getApplicationId() {
        return applicationId;
    }

    /**
     * Sets the application ID.
     *
     * @param applicationId the application ID to set
     */
    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    /**
     * Gets the user ID of the person submitting the application.
     *
     * @return the user ID
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the user ID of the person submitting the application.
     *
     * @param userId the user ID to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the name of the organization applying for the grant.
     *
     * @return the organization name
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
     * Gets the grant ID associated with the application.
     *
     * @return the grant ID
     */
    public int getGrantId() {
        return grantId;
    }

    /**
     * Sets the grant ID associated with the application.
     *
     * @param grantId the grant ID to set
     */
    public void setGrantId(int grantId) {
        this.grantId = grantId;
    }

    /**
     * Gets the status of the application.
     *
     * @return the application status
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
     * Gets the approval date of the application.
     *
     * @return the approval date
     */
    public String getApprovalDate() {
        return approvalDate;
    }

    /**
     * Sets the approval date of the application.
     *
     * @param approvalDate the approval date to set
     */
    public void setApprovalDate(String approvalDate) {
        this.approvalDate = approvalDate;
    }

    /**
     * Gets the requested amount for the grant.
     *
     * @return the requested amount
     */
    public String getRequestedAmount() {
        return requestedAmount;
    }

    /**
     * Sets the requested amount for the grant.
     *
     * @param requestedAmount the requested amount to set
     */
    public void setRequestedAmount(String requestedAmount) {
        this.requestedAmount = requestedAmount;
    }

    /**
     * Gets the project description for which the grant is being requested.
     *
     * @return the project description
     */
    public String getProjectDescription() {
        return projectDescription;
    }

    /**
     * Sets the project description for which the grant is being requested.
     *
     * @param projectDescription the project description to set
     */
    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public boolean getSpecialAward() {
        return specialAward;
    }

    public void setSpecialAward(boolean specialAward) {
        this.specialAward = specialAward;
    }

}
