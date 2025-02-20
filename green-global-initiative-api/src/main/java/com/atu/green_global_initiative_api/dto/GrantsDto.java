package com.atu.green_global_initiative_api.dto;

import lombok.Data;

@Data
public class GrantsDto {
    private int grantId;
    private String grantName;
    private String amount;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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

    public String getEligibility() {
        return eligibility;
    }

    public void setEligibility(String eligibility) {
        this.eligibility = eligibility;
    }

    private String description;
    private String eligibility;
}
