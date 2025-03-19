package com.atu.green_global_initiative_api.service;

import com.atu.green_global_initiative_api.dto.UserDetailsDto;
import com.atu.green_global_initiative_api.model.dao.UserDetails;
import java.util.List;

/**
 * Service interface for managing user-related operations.
 */
public interface UserService {
    /**
     * Retrieves all user details available in the system.
     *
     * @return a list of {@link UserDetailsDto} containing details of all users.
     */
    List<UserDetailsDto> getAllUserDetails();

    /**
     * Registers a new user in the system.
     *
     * @param userDetails the {@link UserDetails} object containing user registration details.
     * @return the {@link UserDetailsDto} object representing the newly registered user.
     */
    UserDetailsDto userSignUp(UserDetails userDetails);

    /**
     * Authenticates a user by their username and password.
     *
     * @param username the username of the user attempting to log in.
     * @param password the password of the user attempting to log in.
     * @return the {@link UserDetailsDto} object representing the authenticated user.
     */
    UserDetailsDto authenticateUser(String username, String password);

    /**
     * Retrieves user details by the user's unique identifier.
     *
     * @param id the unique identifier of the user.
     * @return the {@link UserDetailsDto} object containing details of the specified user.
     */
    UserDetailsDto getAllUserDetailsById(Integer id);

    /**
     * Retrieves user details by the user's email address.
     *
     * @param email the email address of the user.
     * @return the {@link UserDetailsDto} object containing details of the specified user.
     */
    UserDetailsDto getAllUserDetailsByEmail(String email);
}
