package com.atu.green_global_initiative_api.model.dao.request;

/**
 * Request DTO for updating an existing application in the Green Global Initiative API.
 * This class contains the necessary fields to update an application.
 */
public class ApplicationUpdateRequest {

    /** Application ID */
    private int applicationId;

    /** Name of the organization applying for the grant */
    private String organizationName;

    /** Status of the application */
    private String applicationStatus;

    /** Comments from the admin regarding the application */
    private String adminComments;

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
     * Gets the admin comments associated with the application.
     *
     * @return the admin comments
     */
    public String getAdminComments() {
        return adminComments;
    }

    /**
     * Sets the admin comments associated with the application.
     *
     * @param adminComments the admin comments to set
     */
    public void setAdminComments(String adminComments) {
        this.adminComments = adminComments;
    }
}
