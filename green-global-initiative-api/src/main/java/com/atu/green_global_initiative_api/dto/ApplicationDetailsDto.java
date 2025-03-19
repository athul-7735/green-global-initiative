package com.atu.green_global_initiative_api.dto;
import com.atu.green_global_initiative_api.model.dao.Grants;

/**
 * Data Transfer Object (DTO) representing the details of a grant application.
 * This DTO is used to transfer application-related data between layers in the application.
 */
public class ApplicationDetailsDto {
    /**
     * The unique identifier for the application.
     */
    private int applicationId;
    /**
     * The name of the organization applying for the grant.
     */
    private String organizationName;
    /**
     * The current status of the application (e.g., pending, approved, rejected).
     */
    private String applicationStatus;
    /**
     * The user details associated with the application.
     */
    private UserDetailsDto userDetailsDto;
    /**
     * The grant details associated with the application.
     */
    private Grants grants;
    /**
     * The date when the application was approved, if applicable.
     */
    private String approvalDate;
    /**
     * The amount requested by the organization in the application.
     */
    private String requestedAmount;
    /**
     * A brief description of the project for which the grant is being requested.
     */
    private String projectDescription;
    /**
     * Administrative comments or notes related to the application.
     */
    private String adminComments;
    /**
     * Gets the unique identifier for the application.
     *
     * @return the application ID.
     */
    public int getApplicationId() {
        return applicationId;
    }
    /**
     * Sets the unique identifier for the application.
     *
     * @param applicationId the application ID to set.
     */
    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }
    /**
     * Gets the name of the organization applying for the grant.
     *
     * @return the organization name.
     */
    public String getOrganizationName() {
        return organizationName;
    }
    /**
     * Sets the name of the organization applying for the grant.
     *
     * @param organizationName the organization name to set.
     */
    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }
    /**
     * Gets the current status of the application.
     *
     * @return the application status.
     */
    public String getApplicationStatus() {
        return applicationStatus;
    }
    /**
     * Sets the current status of the application.
     *
     * @param applicationStatus the application status to set.
     */
    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }
    /**
     * Gets the user details associated with the application.
     *
     * @return the user details DTO.
     */
    public UserDetailsDto getUserDetailsDto() {
        return userDetailsDto;
    }
    /**
     * Sets the user details associated with the application.
     *
     * @param userDetailsDto the user details DTO to set.
     */
    public void setUserDetailsDto(UserDetailsDto userDetailsDto) {
        this.userDetailsDto = userDetailsDto;
    }

    /**
     * Gets the grant details associated with the application.
     *
     * @return the grant details.
     */
    public Grants getGrants() {
        return grants;
    }

    /**
     * Sets the grant details associated with the application.
     *
     * @param grants the grant details to set.
     */
    public void setGrants(Grants grants) {
        this.grants = grants;
    }

    /**
     * Gets the date when the application was approved.
     *
     * @return the approval date.
     */
    public String getApprovalDate() {
        return approvalDate;
    }

    /**
     * Sets the date when the application was approved.
     *
     * @param approvalDate the approval date to set.
     */
    public void setApprovalDate(String approvalDate) {
        this.approvalDate = approvalDate;
    }

    /**
     * Gets the amount requested by the organization in the application.
     *
     * @return the requested amount.
     */
    public String getRequestedAmount() {
        return requestedAmount;
    }

    /**
     * Sets the amount requested by the organization in the application.
     *
     * @param requestedAmount the requested amount to set.
     */
    public void setRequestedAmount(String requestedAmount) {
        this.requestedAmount = requestedAmount;
    }

    /**
     * Gets the project description provided in the application.
     *
     * @return the project description.
     */
    public String getProjectDescription() {
        return projectDescription;
    }

    /**
     * Sets the project description provided in the application.
     *
     * @param projectDescription the project description to set.
     */
    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    /**
     * Gets the administrative comments or notes related to the application.
     *
     * @return the admin comments.
     */
    public String getAdminComments() {
        return adminComments;
    }

    /**
     * Sets the administrative comments or notes related to the application.
     *
     * @param adminComments the admin comments to set.
     */
    public void setAdminComments(String adminComments) {
        this.adminComments = adminComments;
    }
}
