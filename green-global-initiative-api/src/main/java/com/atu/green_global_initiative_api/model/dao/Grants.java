package com.atu.green_global_initiative_api.model.dao;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Data
@Table(name="grants")
public class Grants {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="grant_id", unique = true, nullable = false)
    private int grantId;
    @Column(name="grant_name",nullable = false)
    private String grantName;
    @Column(name="amount", nullable = false)
    private String amount;
    @Column(name="description", nullable = false)
    private String description;
    @Column(name="eligibility", nullable = false)
    private String eligibility;

    @OneToMany(mappedBy = "grants", cascade = CascadeType.ALL)
    private List<ApplicationDetails> applicationDetails;

    public int getGrantId() {
        return grantId;
    }

    public void setGrantId(int grantId) {
        this.grantId = grantId;
    }

    public String getGrantName() {
        return grantName;
    }

    public void setGrantName(String grantName) {
        this.grantName = grantName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEligibility() {
        return eligibility;
    }

    public void setEligibility(String eligibility) {
        this.eligibility = eligibility;
    }

    public List<ApplicationDetails> getApplicationDetails() {
        return applicationDetails;
    }

    public void setApplicationDetails(List<ApplicationDetails> applicationDetails) {
        this.applicationDetails = applicationDetails;
    }
}