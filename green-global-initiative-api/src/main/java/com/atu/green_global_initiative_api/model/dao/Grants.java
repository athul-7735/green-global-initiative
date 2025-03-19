package com.atu.green_global_initiative_api.model.dao;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

/**
 * Represents a grant in the system, which includes details such as the grant's name,
 * the amount of money available, eligibility criteria, and a description.
 * This entity is mapped to the "grants" table in the database.
 *
 * <p>Each grant can have multiple applications associated with it, represented by the
 * "applicationDetails" list.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * Grants grant = new Grants();
 * grant.setGrantName("Climate Change Initiative");
 * grant.setAmount("50000 USD");
 * grant.setDescription("Funding to support projects combating climate change.");
 * grant.setEligibility("Must be an organization working in environmental protection.");
 * </pre>
 *
 * <p>This entity is used in the persistence layer to manage grant details and their associated applications.</p>
 *
 * @author John Kirubaharan Ravichandran
 * @since 2025-03-19
 */
@Entity
@Data
@Table(name="grants")
public class Grants {

    /**
     * Unique identifier for the grant.
     * This value is automatically generated by the database upon insertion.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="grant_id", unique = true, nullable = false)
    private int grantId;

    /**
     * The name of the grant.
     * This field cannot be null.
     */
    @Column(name="grant_name",nullable = false)
    private String grantName;

    /**
     * The total amount of money allocated for the grant.
     * This field cannot be null.
     */
    @Column(name="amount", nullable = false)
    private String amount;

    /**
     * A description of the grant, outlining its purpose and goals.
     * This field cannot be null.
     */
    @Column(name="description", nullable = false)
    private String description;

    /**
     * The eligibility criteria for receiving the grant.
     * This field cannot be null.
     */
    @Column(name="eligibility", nullable = false)
    private String eligibility;

    /**
     * A list of applications associated with this grant.
     * A one-to-many relationship is established where multiple applications can be linked to one grant.
     * Cascading operations are enabled for all related application details.
     */
    @OneToMany(mappedBy = "grants", cascade = CascadeType.ALL)
    private List<ApplicationDetails> applicationDetails;

    /**
     * Gets the unique identifier of the grant.
     *
     * @return the grant's unique ID.
     */
    public int getGrantId() {
        return grantId;
    }

    /**
     * Sets the unique identifier of the grant.
     *
     * @param grantId the grant's unique ID.
     */
    public void setGrantId(int grantId) {
        this.grantId = grantId;
    }

    /**
     * Gets the name of the grant.
     *
     * @return the name of the grant.
     */
    public String getGrantName() {
        return grantName;
    }

    /**
     * Sets the name of the grant.
     *
     * @param grantName the name of the grant.
     */
    public void setGrantName(String grantName) {
        this.grantName = grantName;
    }

    /**
     * Gets the amount allocated for the grant.
     *
     * @return the amount of the grant.
     */
    public String getAmount() {
        return amount;
    }

    /**
     * Sets the amount allocated for the grant.
     *
     * @param amount the amount of the grant.
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     * Gets the description of the grant.
     *
     * @return the description of the grant.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the grant.
     *
     * @param description the description of the grant.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the eligibility criteria for the grant.
     *
     * @return the eligibility criteria for the grant.
     */
    public String getEligibility() {
        return eligibility;
    }

    /**
     * Sets the eligibility criteria for the grant.
     *
     * @param eligibility the eligibility criteria for the grant.
     */
    public void setEligibility(String eligibility) {
        this.eligibility = eligibility;
    }

    /**
     * Gets the list of applications associated with this grant.
     *
     * @return the list of application details.
     */
    public List<ApplicationDetails> getApplicationDetails() {
        return applicationDetails;
    }

    /**
     * Sets the list of applications associated with this grant.
     *
     * @param applicationDetails the list of application details.
     */
    public void setApplicationDetails(List<ApplicationDetails> applicationDetails) {
        this.applicationDetails = applicationDetails;
    }
}