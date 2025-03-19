package com.atu.green_global_initiative_api.service;

import com.atu.green_global_initiative_api.dto.UserDetailsDto;
import com.atu.green_global_initiative_api.model.dao.UserDetails;
import java.util.List;

/**
 * Service interface for handling user-related operations.
 * Provides methods for user authentication, signup, and fetching user details.
 */
public interface UserService {

    /**
     * Retrieves a list of all user details.
     *
     * @return a list of UserDetailsDto objects representing all registered users.
     */
    List<UserDetailsDto> getAllUserDetails();

    /**
     * Signs up a new user by creating a new UserDetails object.
     *
     * @param userDetails the UserDetails object containing the information of the user to be registered.
     * @return a UserDetailsDto object representing the newly registered user.
     */
    UserDetailsDto userSignUp(UserDetails userDetails);

    /**
     * Authenticates a user based on their username and password.
     *
     * @param username the username of the user attempting to authenticate.
     * @param password the password of the user attempting to authenticate.
     * @return a UserDetailsDto object representing the authenticated user, or null if authentication fails.
     */
    UserDetailsDto authenticateUser(String username, String password);

    /**
     * Retrieves user details by the user's unique ID.
     *
     * @param id the unique identifier of the user whose details are to be retrieved.
     * @return a UserDetailsDto object representing the user with the specified ID.
     */
    UserDetailsDto getAllUserDetailsById(Integer id);

    /**
     * Retrieves user details by the user's email address.
     *
     * @param email the email address of the user whose details are to be retrieved.
     * @return a UserDetailsDto object representing the user with the specified email address.
     */
    UserDetailsDto getAllUserDetailsByEmail(String email);
}
