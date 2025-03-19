package com.atu.green_global_initiative_api.dto;

import com.atu.green_global_initiative_api.model.dao.Grants;
/**
 * Data Transfer Object (DTO) for holding application details in the Green Global Initiative API.
 */
public class ApplicationDetailsDto {
    /** Application ID */
    private int applicationId;

    /** Organization name applying for the grant */
    private String organizationName;

    /** Status of the application */
    private String applicationStatus;

    /** User details associated with the application */
    private UserDetailsDto userDetailsDto;

    /** Grants associated with the application */
    private Grants grants;

    /** Date when the application was approved */
    private String approvalDate;

    /** Requested amount for the project */
    private String requestedAmount;

    /** Description of the project for which the grant is requested */
    private String projectDescription;

    /** Comments made by the admin regarding the application */
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
     * Gets the organization name.
     *
     * @return the organization name
     */
    public String getOrganizationName() {
        return organizationName;
    }

    /**
     * Sets the organization name.
     *
     * @param organizationName the organization name to set
     */
    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    /**
     * Gets the application status.
     *
     * @return the application status
     */
    public String getApplicationStatus() {
        return applicationStatus;
    }

    /**
     * Sets the application status.
     *
     * @param applicationStatus the application status to set
     */
    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    /**
     * Gets the user details associated with the application.
     *
     * @return the user details DTO
     */
    public UserDetailsDto getUserDetailsDto() {
        return userDetailsDto;
    }

    /**
     * Sets the user details associated with the application.
     *
     * @param userDetailsDto the user details DTO to set
     */
    public void setUserDetailsDto(UserDetailsDto userDetailsDto) {
        this.userDetailsDto = userDetailsDto;
    }

    /**
     * Gets the grants associated with the application.
     *
     * @return the grants
     */
    public Grants getGrants() {
        return grants;
    }

    /**
     * Sets the grants associated with the application.
     *
     * @param grants the grants to set
     */
    public void setGrants(Grants grants) {
        this.grants = grants;
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
     * Gets the requested amount for the project.
     *
     * @return the requested amount
     */
    public String getRequestedAmount() {
        return requestedAmount;
    }

    /**
     * Sets the requested amount for the project.
     *
     * @param requestedAmount the requested amount to set
     */
    public void setRequestedAmount(String requestedAmount) {
        this.requestedAmount = requestedAmount;
    }

    /**
     * Gets the project description.
     *
     * @return the project description
     */
    public String getProjectDescription() {
        return projectDescription;
    }

    /**
     * Sets the project description.
     *
     * @param projectDescription the project description to set
     */
    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    /**
     * Gets the admin comments for the application.
     *
     * @return the admin comments
     */
    public String getAdminComments() {
        return adminComments;
    }

    /**
     * Sets the admin comments for the application.
     *
     * @param adminComments the admin comments to set
     */
    public void setAdminComments(String adminComments) {
        this.adminComments = adminComments;
    }
}
