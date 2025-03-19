package com.atu.green_global_initiative_api.model.dao.request;
/**
 * This class represents the request body for updating an application in the Green Global Initiative API.
 * It contains fields that allow for modifications to the application's details, such as the organization name,
 * application status, and any admin comments.
 */
public class ApplicationUpdateRequest {
    /**
     * The unique identifier for the application being updated.
     */
    private int applicationId;
    /**
     * The name of the organization associated with the application.
     */
    private String organizationName;
    /**
     * The current status of the application (e.g., under review, approved, rejected).
     */
    private String applicationStatus;
    /**
     * Comments or feedback from the admin regarding the application.
     */
    private String adminComments;
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
     * Gets the name of the organization associated with the application.
     *
     * @return the organization name
     */
    public String getOrganizationName() {
        return organizationName;
    }
    /**
     * Sets the name of the organization associated with the application.
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
     * Gets the admin comments or feedback regarding the application.
     *
     * @return the admin comments
     */
    public String getAdminComments() {
        return adminComments;
    }
    /**
     * Sets the admin comments or feedback regarding the application.
     *
     * @param adminComments the new admin comments
     */
    public void setAdminComments(String adminComments) {
        this.adminComments = adminComments;
    }
}
