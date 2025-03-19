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
 * Implementation of the {@link UserService} interface for managing user-related operations.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDetailsRepo userDetailsRepo;

    static final Logger logger = LoggerFactory.getLogger(NausicaaGreenInitiativeApplication.class);
    /**
     * Retrieves all user details from the database.
     *
     * @return a list of {@link UserDetailsDto} objects representing all users.
     */
//    @Autowired
//    private PasswordEncoder passwordEncoder;

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
     * Retrieves user details by the unique user ID.
     *
     * @param userId the unique ID of the user.
     * @return the {@link UserDetailsDto} object representing the user.
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
     * Retrieves user details by email.
     *
     * @param email the email address of the user.
     * @return the {@link UserDetailsDto} object representing the user.
     */
    @Override
    public UserDetailsDto getAllUserDetailsByEmail(String email) {
        logger.info("getAllUserDetailsByEmail Method Started");
        List<UserDetails> userDetailsList = userDetailsRepo.findAllByEmail(email);
        List<UserDetailsDto> userDetailsDtoList = new ArrayList<>();
        for (UserDetails userDetails : userDetailsList) {
            userDetailsDtoList.add(mapToUserDto(userDetails));
        }
        return userDetailsDtoList.getFirst();
    }

    /**
     * Registers a new user in the system.
     *
     * @param userDetails the {@link UserDetails} object containing user information.
     * @return the {@link UserDetailsDto} object representing the newly registered user.
     */
    @Override
    public UserDetailsDto userSignUp(UserDetails userDetails) {
        logger.info("userSignUp Method Started");
        UserDetails res = userDetailsRepo.save(userDetails);
        UserDetailsDto userDetailsDto = mapToUserDto(res);
        return userDetailsDto;
    }

    /**
     * Authenticates a user using their username and password.
     *
     * @param username the username of the user.
     * @param password the password of the user.
     * @return the {@link UserDetailsDto} object if authentication is successful, or null otherwise.
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
     * Maps a {@link UserDetails} object to a {@link UserDetailsDto}.
     *
     * @param userDetails the {@link UserDetails} object to be mapped.
     * @return the corresponding {@link UserDetailsDto} object.
     */
    static UserDetailsDto mapToUserDto(UserDetails userDetails){
        logger.info("mapToUserDto Method Started");
        UserDetailsDto userDetailsDto = new UserDetailsDto();
        userDetailsDto.setUserId(userDetails.getUserId());
        userDetailsDto.setFirstName(userDetails.getFirstName());
        userDetailsDto.setLastName(userDetails.getLastName());
        userDetailsDto.setEmail(userDetails.getEmail());
        userDetailsDto.setAdmin(userDetails.isAdmin());
        List<ApplicationDetails> applicationDetailsList  = new ArrayList<>();
        if(userDetails.getApplicationDetails() != null && !userDetails.getApplicationDetails().isEmpty()){
            for(ApplicationDetails applicationDetails : userDetails.getApplicationDetails()){
                ApplicationDetails applicationDetail = new ApplicationDetails();
                applicationDetail.setApplicationId(applicationDetails.getApplicationId());
                applicationDetail.setApplicationStatus(applicationDetails.getApplicationStatus());
                applicationDetail.setOrganizationName(applicationDetails.getOrganizationName());
                applicationDetail.setRequestedAmount(applicationDetails.getRequestedAmount());
                applicationDetail.setProjectDescription(applicationDetails.getProjectDescription());
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
