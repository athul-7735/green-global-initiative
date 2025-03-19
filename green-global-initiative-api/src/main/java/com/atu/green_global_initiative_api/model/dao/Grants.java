package com.atu.green_global_initiative_api.model.dao;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

/**
 * Represents a grant available under the Green Global Initiative.
 * The grant contains information such as its name, amount, eligibility criteria, description,
 * and associated application details.
 */
@Entity
@Data
@Table(name="grants")
public class Grants {
    /**
     * Unique identifier for the grant.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="grant_id", unique = true, nullable = false)
    private int grantId;
    /**
     * The name of the grant.
     */
    @Column(name="grant_name",nullable = false)
    private String grantName;

    /**
     * The amount allocated for the grant.
     */
    @Column(name="amount", nullable = false)
    private String amount;

    /**
     * A description of the grant, outlining its purpose and details.
     */
    @Column(name="description", nullable = false)
    private String description;

    /**
     * The eligibility criteria for applicants seeking the grant.
     */
    @Column(name="eligibility", nullable = false)
    private String eligibility;

    /**
     * A list of application details associated with this grant.
     * This relationship indicates the grants applied for by users.
     */
    @OneToMany(mappedBy = "grants", cascade = CascadeType.ALL)
    private List<ApplicationDetails> applicationDetails;

    /**
     * Gets the unique identifier for the grant.
     *
     * @return the grant ID
     */
    public int getGrantId() {
        return grantId;
    }

    /**
     * Sets the unique identifier for the grant.
     *
     * @param grantId the grant ID to set
     */
    public void setGrantId(int grantId) {
        this.grantId = grantId;
    }

    /**
     * Gets the name of the grant.
     *
     * @return the grant name
     */
    public String getGrantName() {
        return grantName;
    }

    /**
     * Sets the name of the grant.
     *
     * @param grantName the grant name to set
     */
    public void setGrantName(String grantName) {
        this.grantName = grantName;
    }

    /**
     * Gets the amount allocated for the grant.
     *
     * @return the amount of the grant
     */
    public String getAmount() {
        return amount;
    }

    /**
     * Sets the amount allocated for the grant.
     *
     * @param amount the amount to set for the grant
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     * Gets the description of the grant.
     *
     * @return the description of the grant
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description for the grant.
     *
     * @param description the description to set for the grant
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the eligibility criteria for the grant.
     *
     * @return the eligibility criteria for the grant
     */
    public String getEligibility() {
        return eligibility;
    }

    /**
     * Sets the eligibility criteria for the grant.
     *
     * @param eligibility the eligibility criteria to set for the grant
     */
    public void setEligibility(String eligibility) {
        this.eligibility = eligibility;
    }

    /**
     * Gets the list of application details for this grant.
     *
     * @return a list of application details associated with the grant
     */
    public List<ApplicationDetails> getApplicationDetails() {
        return applicationDetails;
    }

    /**
     * Sets the list of application details associated with this grant.
     *
     * @param applicationDetails the list of application details to set
     */
    public void setApplicationDetails(List<ApplicationDetails> applicationDetails) {
        this.applicationDetails = applicationDetails;
    }
}