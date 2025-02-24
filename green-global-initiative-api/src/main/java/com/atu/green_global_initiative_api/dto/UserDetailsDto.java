package com.atu.green_global_initiative_api.dto;

import com.atu.green_global_initiative_api.model.dao.ApplicationDetails;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class UserDetailsDto {
    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private boolean isAdmin;
    private String lastLogin;

    List<ApplicationDetails> applicationDetails;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public List<ApplicationDetails> getApplicationDetails() {
        return applicationDetails;
    }

    public void setApplicationDetails(List<ApplicationDetails> applicationDetails) {
        this.applicationDetails = applicationDetails;
    }
}
