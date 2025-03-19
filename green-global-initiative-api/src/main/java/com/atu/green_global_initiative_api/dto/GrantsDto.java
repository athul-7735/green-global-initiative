package com.atu.green_global_initiative_api.dto;

import lombok.Data;
/**
 * Data Transfer Object (DTO) for Grants.
 * This class is used to transfer grant-related information between layers of the application.
 */
@Data
public class GrantsDto {
    /**
     * Unique identifier for the grant.
     */
    private int grantId;
    /**
     * Name of the grant.
     */
    private String grantName;
    /**
     * Amount of the grant.
     */
    private String amount;

    /**
     * Description of the grant, providing additional details.
     *
     * @return A string containing the description of the grant.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description for the grant.
     *
     * @param description A string representing the description of the grant.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the unique identifier for the grant.
     *
     * @return The grant's unique ID.
     */
    public int getGrantId() {
        return grantId;
    }

    /**
     * Sets the unique identifier for the grant.
     *
     * @param grantId The unique ID of the grant.
     */
    public void setGrantId(int grantId) {
        this.grantId = grantId;
    }

    /**
     * Gets the name of the grant.
     *
     * @return The name of the grant.
     */
    public String getGrantName() {
        return grantName;
    }

    /**
     * Sets the name of the grant.
     *
     * @param grantName The name of the grant.
     */
    public void setGrantName(String grantName) {
        this.grantName = grantName;
    }

    /**
     * Gets the amount of the grant.
     *
     * @return The amount of the grant.
     */
    public String getAmount() {
        return amount;
    }

    /**
     * Sets the amount for the grant.
     *
     * @param amount The amount of the grant.
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     * Gets the eligibility criteria for the grant.
     *
     * @return The eligibility criteria for the grant.
     */
    public String getEligibility() {
        return eligibility;
    }

    /**
     * Sets the eligibility criteria for the grant.
     *
     * @param eligibility The eligibility criteria for the grant.
     */
    public void setEligibility(String eligibility) {
        this.eligibility = eligibility;
    }

    /**
     * Description of the grant.
     * Provides additional details about the grant.
     */
    private String description;

    /**
     * Eligibility criteria for the grant.
     * Provides information about who can apply for the grant.
     */
    private String eligibility;
}
