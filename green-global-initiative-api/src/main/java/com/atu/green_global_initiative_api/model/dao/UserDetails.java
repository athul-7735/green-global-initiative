package com.atu.green_global_initiative_api.model.dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Data
@Getter
@Setter
@Table(name="user_details")
public class UserDetails {
    @Id
    @JsonProperty("user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id", unique = true, nullable = false)
    private int userId;
    @JsonProperty("first_name")
    @Column(name="first_name",nullable = false)
    private String firstName;
    @JsonProperty("last_name")
    @Column(name="last_name",nullable = false)
    private String lastName;
    @JsonProperty("email")
    @Column(name="email",nullable = false)
    private String email;
    @JsonProperty("password")
    @Column(name="password",nullable = false)
    private String password;
    @JsonProperty("is_admin")
    @Column(name="isAdmin",nullable = false)
    private boolean isAdmin;
    @JsonProperty("last_login")
    @Column(name="last_login",nullable = false)
    private String lastLogin;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userDetails")
//    private List<ApplicationDetails> applicationDetailsList;

    @OneToMany(mappedBy = "userDetails", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ApplicationDetails> applicationDetails;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
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
