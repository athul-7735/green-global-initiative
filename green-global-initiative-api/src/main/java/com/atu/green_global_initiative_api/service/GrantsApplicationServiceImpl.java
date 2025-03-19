package com.atu.green_global_initiative_api.service;

import com.atu.green_global_initiative_api.dto.ApplicationDetailsDto;
import com.atu.green_global_initiative_api.dto.UserDetailsDto;
import com.atu.green_global_initiative_api.model.dao.ApplicationDetails;
import com.atu.green_global_initiative_api.model.dao.Grants;
import com.atu.green_global_initiative_api.model.dao.UserDetails;
import com.atu.green_global_initiative_api.model.dao.request.ApplicationCreateRequest;
import com.atu.green_global_initiative_api.model.dao.request.ApplicationUpdateRequest;
import com.atu.green_global_initiative_api.repository.ApplicationDetailsRepo;
import com.atu.green_global_initiative_api.repository.GrantsRepo;
import com.atu.green_global_initiative_api.repository.UserDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * Service implementation for handling operations related to grant applications.
 */
@Service
public class GrantsApplicationServiceImpl implements GrantsApplicationService {

    @Autowired
    ApplicationDetailsRepo applicationDetailsRepo;

    @Autowired
    private UserDetailsRepo userDetailsRepo;

    @Autowired
    private GrantsRepo grantsRepo;

    /**
     * Creates new application details based on the provided request.
     *
     * @param applicationCreateRequest the request containing details of the new application.
     * @return a list of created application details DTOs or null if an application with the same ID already exists.
     */
    @Override
    public List<ApplicationDetailsDto> createApplicationDetails(ApplicationCreateRequest applicationCreateRequest) {
       List<ApplicationDetails> applicationDetailsList = applicationDetailsRepo.findAllByApplicationId(applicationCreateRequest.getApplicationId());
       if(!applicationDetailsList.isEmpty()){ // if exists, do not create the application
           return null;
       }
        ApplicationDetails applicationDetails = new ApplicationDetails();
        applicationDetails.setApplicationId(applicationCreateRequest.getApplicationId());
        applicationDetails.setOrganizationName(applicationCreateRequest.getOrganizationName());
        applicationDetails.setApplicationStatus(applicationCreateRequest.getApplicationStatus());
        applicationDetails.setApprovalDate(applicationCreateRequest.getApprovalDate());
        applicationDetails.setRequestedAmount(applicationCreateRequest.getRequestedAmount());
        applicationDetails.setProjectDescription(applicationCreateRequest.getProjectDescription());
        List<UserDetails> userDetailsList = userDetailsRepo.findAllById(applicationCreateRequest.getUserId());
        if(!userDetailsList.isEmpty()){
            applicationDetails.setUserDetails(userDetailsList.getFirst());
        }
        List<Grants> grants = grantsRepo.findAllById(applicationCreateRequest.getGrantId());
        if(!grants.isEmpty()){
            applicationDetails.setGrants(grants.get(0));
        }
        ApplicationDetails applicationDetailsResponse = applicationDetailsRepo.save(applicationDetails);
        List<ApplicationDetails> applicationDetailsResponseList = new ArrayList<>();
        applicationDetailsResponseList.add(applicationDetailsResponse);
        return mapToApplicationDetailsDto(applicationDetailsResponseList);
    }
    /**
     * Retrieves all application details by the provided application ID.
     *
     * @param applicationId the ID of the application to retrieve details for.
     * @return a list of application details DTOs for the specified application ID.
     */
    @Override
    public List<ApplicationDetailsDto> getAllApplicationDetailsByApplicationId(String applicationId) {
        List<ApplicationDetails> applicationDetailsList = applicationDetailsRepo.findAllByApplicationId(Integer.parseInt(applicationId));
        List<ApplicationDetailsDto> applicationDetailsDtoList = mapToApplicationDetailsDto(applicationDetailsList);
        return applicationDetailsDtoList;
    }
    /**
     * Retrieves all application details.
     *
     * @return a list of all application details DTOs.
     */
    @Override
    public List<ApplicationDetailsDto> getAllApplicationDetails() {
        List<ApplicationDetails> applicationDetailsList = applicationDetailsRepo.findAll();
        return mapToApplicationDetailsDto(applicationDetailsList);
    }
    /**
     * Maps a list of ApplicationDetails objects to a list of ApplicationDetailsDto objects.
     *
     * @param applicationDetailsList the list of ApplicationDetails objects to map.
     * @return a list of ApplicationDetailsDto objects.
     */
    private static List<ApplicationDetailsDto> mapToApplicationDetailsDto(List<ApplicationDetails> applicationDetailsList) {
        List<ApplicationDetailsDto> applicationDetailsDtoList = new ArrayList<ApplicationDetailsDto>();
        for (ApplicationDetails applicationDetails : applicationDetailsList) {
            ApplicationDetailsDto applicationDetailsDto = new ApplicationDetailsDto();
            applicationDetailsDto.setApplicationId(applicationDetails.getApplicationId());
            applicationDetailsDto.setOrganizationName(applicationDetails.getOrganizationName());
            applicationDetailsDto.setApplicationStatus(applicationDetails.getApplicationStatus());
            applicationDetailsDto.setApprovalDate(applicationDetails.getApprovalDate());
            applicationDetailsDto.setProjectDescription(applicationDetails.getProjectDescription());
            applicationDetailsDto.setAdminComments(applicationDetails.getAdminComments());
            applicationDetailsDto.setRequestedAmount(applicationDetails.getRequestedAmount());
            // Mapping grants data to DTO
            Grants grants = new Grants();
            grants.setGrantId(applicationDetails.getGrants().getGrantId());
            grants.setGrantName(applicationDetails.getGrants().getGrantName());
            grants.setDescription(applicationDetails.getGrants().getDescription());
            grants.setAmount(applicationDetails.getGrants().getAmount());
            grants.setEligibility(applicationDetails.getGrants().getEligibility());
            applicationDetailsDto.setGrants(grants);
            // Mapping user details to DTO
            UserDetails userDetails = new UserDetails();
            userDetails.setUserId(applicationDetails.getUserDetails().getUserId());
            userDetails.setEmail(applicationDetails.getUserDetails().getEmail());
            userDetails.setFirstName(applicationDetails.getUserDetails().getFirstName());
            userDetails.setLastName(applicationDetails.getUserDetails().getLastName());
            UserDetailsDto userDetailsDto = UserServiceImpl.mapToUserDto(userDetails);
            applicationDetailsDto.setUserDetailsDto(userDetailsDto);
            applicationDetailsDtoList.add(applicationDetailsDto);
        }
        return applicationDetailsDtoList;
    }
    /**
     * Updates the details of an existing application.
     *
     * @param applicationUpdateRequest the request containing the updated application details.
     * @return a list of updated application details DTOs or null if no application with the specified ID is found.
     */
    @Override
    public List<ApplicationDetailsDto> updateApplicationDetails(ApplicationUpdateRequest applicationUpdateRequest) {
        List<ApplicationDetails> applicationDetailsList = new ArrayList<>();

        applicationDetailsList = applicationDetailsRepo.findAllByApplicationId(applicationUpdateRequest.getApplicationId());
        if (applicationDetailsList.isEmpty()) {
            return null;
        } else {
            ApplicationDetails applicationDetails = applicationDetailsList.get(0);
            applicationDetails.setApplicationStatus(applicationUpdateRequest.getApplicationStatus());
            applicationDetails.setAdminComments(applicationUpdateRequest.getAdminComments());
            ApplicationDetails applicationDetailsResponse = applicationDetailsRepo.save(applicationDetails);
            List<ApplicationDetails> applicationDetailsResponseList = new ArrayList<>();
            applicationDetailsResponseList.add(applicationDetailsResponse);
            return mapToApplicationDetailsDto(applicationDetailsResponseList);
        }
    }


}
