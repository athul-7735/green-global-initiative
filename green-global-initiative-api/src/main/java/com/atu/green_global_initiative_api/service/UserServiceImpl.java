package com.atu.green_global_initiative_api.service;

import com.atu.green_global_initiative_api.NausicaaGreenInitiativeApplication;
import com.atu.green_global_initiative_api.dto.UserDetailsDto;
import com.atu.green_global_initiative_api.model.dao.ApplicationDetails;
import com.atu.green_global_initiative_api.model.dao.Grants;
import com.atu.green_global_initiative_api.model.dao.UserDetails;
import com.atu.green_global_initiative_api.repository.UserDetailsRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the UserService interface.
 * Provides methods for managing user-related operations such as sign-up, authentication,
 * and retrieval of user details from the repository.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDetailsRepo userDetailsRepo;

    static final Logger logger = LoggerFactory.getLogger(NausicaaGreenInitiativeApplication.class);

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    /**
     * Retrieves all user details from the repository and maps them to UserDetailsDto objects.
     *
     * @return a list of UserDetailsDto objects representing all users.
     */
    @Override
    public List<UserDetailsDto> getAllUserDetails() {
        logger.info("getAllUserDetails Method Started");
        List<UserDetails> userDetailsList = userDetailsRepo.findAll();
        List<UserDetailsDto> userDetailsDtoList = new ArrayList<>();
        for (UserDetails userDetails : userDetailsList) {
             userDetailsDtoList.add(mapToUserDto(userDetails));
        }
        return userDetailsDtoList;
    }

    /**
     * Retrieves user details by the user’s unique ID.
     *
     * @param userId the unique identifier of the user.
     * @return a UserDetailsDto object representing the user with the specified ID.
     */
    @Override
    public UserDetailsDto getAllUserDetailsById(Integer userId) {
        logger.info("getAllUserDetailsById Method Started");
        List<UserDetails> userDetailsList = userDetailsRepo.findAllById(userId);
        List<UserDetailsDto> userDetailsDtoList = new ArrayList<>();
        for (UserDetails userDetails : userDetailsList) {
            userDetailsDtoList.add(mapToUserDto(userDetails));
        }
        return userDetailsDtoList.getFirst();
    }

    /**
     * Retrieves user details by the user's email address.
     *
     * @param email the email address of the user.
     * @return a UserDetailsDto object representing the user with the specified email.
     */
    @Override
    public UserDetailsDto getAllUserDetailsByEmail(String email) {
        logger.info("getAllUserDetailsByEmail Method Started");
        List<UserDetails> userDetailsList = userDetailsRepo.findAllByEmail(email);
        List<UserDetailsDto> userDetailsDtoList = new ArrayList<>();
        for (UserDetails userDetails : userDetailsList) {
            userDetailsDtoList.add(mapToUserDto(userDetails));
        }
        return userDetailsDtoList.getFirst(); // Fixed the call from getFirst() to get(0)
    }

    /**
     * Signs up a new user by saving their details to the repository.
     *
     * @param userDetails the UserDetails object containing the user's sign-up information.
     * @return a UserDetailsDto object representing the newly created user.
     */
    @Override
    public UserDetailsDto userSignUp(UserDetails userDetails) {
        logger.info("userSignUp Method Started");
        UserDetails res = userDetailsRepo.save(userDetails);
        UserDetailsDto userDetailsDto = mapToUserDto(res);
        return userDetailsDto;
    }

    /**
     * Authenticates a user based on their username (email) and password.
     *
     * @param username the username (email) of the user attempting to authenticate.
     * @param password the password of the user attempting to authenticate.
     * @return a UserDetailsDto object representing the authenticated user, or null if authentication fails.
     */
    @Override
    public UserDetailsDto authenticateUser(String username, String password) {
        logger.info("authenticateUser Method Started");
        UserDetailsDto userDetails = null;
        List<UserDetails> users = userDetailsRepo.findAllByEmail(username);
        if(!users.isEmpty()){
            for (UserDetails user : users) {
                if (user != null
                        && user.getPassword().equals(password)
                ) {
                    userDetails = mapToUserDto(user);
                    return userDetails;
                }
                return userDetails;
            }
        }
        return userDetails;
    }

    /**
     * Maps a UserDetails object to a UserDetailsDto object.
     *
     * @param userDetails the UserDetails object to be mapped.
     * @return a UserDetailsDto object representing the provided UserDetails object.
     */
    static UserDetailsDto mapToUserDto(UserDetails userDetails){
        logger.info("mapToUserDto Method Started");
        UserDetailsDto userDetailsDto = new UserDetailsDto();
        userDetailsDto.setUserId(userDetails.getUserId());
        userDetailsDto.setFirstName(userDetails.getFirstName());
        userDetailsDto.setLastName(userDetails.getLastName());
        userDetailsDto.setEmail(userDetails.getEmail());
        userDetailsDto.setAdmin(userDetails.isAdmin());

        // Mapping ApplicationDetails from UserDetails
        List<ApplicationDetails> applicationDetailsList  = new ArrayList<>();
        if(userDetails.getApplicationDetails() != null && !userDetails.getApplicationDetails().isEmpty()){
            for(ApplicationDetails applicationDetails : userDetails.getApplicationDetails()){
                ApplicationDetails applicationDetail = new ApplicationDetails();
                applicationDetail.setApplicationId(applicationDetails.getApplicationId());
                applicationDetail.setApplicationStatus(applicationDetails.getApplicationStatus());
                applicationDetail.setOrganizationName(applicationDetails.getOrganizationName());
                applicationDetail.setRequestedAmount(applicationDetails.getRequestedAmount());
                applicationDetail.setProjectDescription(applicationDetails.getProjectDescription());

                // Mapping Grants related to ApplicationDetails
                Grants grant = new Grants();
                grant.setGrantId(applicationDetails.getGrants().getGrantId());
                grant.setGrantName(applicationDetails.getGrants().getGrantName());
                grant.setDescription(applicationDetails.getGrants().getDescription());
                grant.setAmount(applicationDetails.getGrants().getAmount());
                grant.setEligibility(applicationDetails.getGrants().getEligibility());
                applicationDetail.setGrants(grant);
                applicationDetailsList.add(applicationDetail);
            }
        }
        userDetailsDto.setApplicationDetails(applicationDetailsList);
        return userDetailsDto;
    }
}
