package com.atu.green_global_initiative_api.model.dao.request;

import lombok.Data;
/**
 * This class represents the request body for creating an application in the Green Global Initiative API.
 * It contains information about the application such as the user ID, organization, grant, and requested details.
 */
@Data
public class ApplicationCreateRequest {
    /**
     * The unique identifier for the application.
     */
    private int applicationId;
    /**
     * The unique identifier for the user submitting the application.
     */
    private int userId;
    /**
     * The name of the organization submitting the application.
     */
    private String organizationName;
    /**
     * The unique identifier for the grant the application is requesting.
     */
    private int grantId;
    /**
     * The current status of the application (e.g., submitted, approved, rejected).
     */
    private String applicationStatus;
    /**
     * The approval date for the application, if applicable.
     */
    public String approvalDate;
    /**
     * The amount of funding requested in the application.
     */
    public String requestedAmount;
    /**
     * A detailed description of the project for which the application is submitted.
     */
    public String projectDescription;
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
     * Gets the unique identifier for the user submitting the application.
     *
     * @return the user ID
     */
    public int getUserId() {
        return userId;
    }
    /**
     * Sets the unique identifier for the user submitting the application.
     *
     * @param userId the new user ID
     */
    public void setUserId(int userId) {
        this.userId = userId;
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
     * Gets the unique identifier for the grant the application is requesting.
     *
     * @return the grant ID
     */
    public int getGrantId() {
        return grantId;
    }
    /**
     * Sets the unique identifier for the grant the application is requesting.
     *
     * @param grantId the new grant ID
     */
    public void setGrantId(int grantId) {
        this.grantId = grantId;
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
}
