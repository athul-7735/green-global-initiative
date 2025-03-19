package com.atu.green_global_initiative_api.dto;

import lombok.Data;

/**
 * Data Transfer Object (DTO) for holding grant details in the Green Global Initiative API.
 */
@Data
public class GrantsDto {

    /** Grant ID */
    private int grantId;

    /** Grant name */
    private String grantName;

    /** Grant amount */
    private String amount;

    /**
     * Gets the description of the grant.
     *
     * @return the description of the grant
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the grant.
     *
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the grant ID.
     *
     * @return the grant ID
     */
    public int getGrantId() {
        return grantId;
    }

    /**
     * Sets the grant ID.
     *
     * @param grantId the grant ID to set
     */
    public void setGrantId(int grantId) {
        this.grantId = grantId;
    }

    /**
     * Gets the grant name.
     *
     * @return the grant name
     */
    public String getGrantName() {
        return grantName;
    }

    /**
     * Sets the grant name.
     *
     * @param grantName the grant name to set
     */
    public void setGrantName(String grantName) {
        this.grantName = grantName;
    }

    /**
     * Gets the amount of the grant.
     *
     * @return the grant amount
     */
    public String getAmount() {
        return amount;
    }

    /**
     * Sets the amount of the grant.
     *
     * @param amount the grant amount to set
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     * Gets the eligibility criteria for the grant.
     *
     * @return the eligibility criteria
     */
    public String getEligibility() {
        return eligibility;
    }

    /**
     * Sets the eligibility criteria for the grant.
     *
     * @param eligibility the eligibility criteria to set
     */
    public void setEligibility(String eligibility) {
        this.eligibility = eligibility;
    }

    /** Description of the grant */
    private String description;

    /** Eligibility criteria for the grant */
    private String eligibility;
}
