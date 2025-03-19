package com.atu.green_global_initiative_api.service;


import com.atu.green_global_initiative_api.dto.ApplicationDetailsDto;
import com.atu.green_global_initiative_api.model.dao.request.ApplicationCreateRequest;
import com.atu.green_global_initiative_api.model.dao.request.ApplicationUpdateRequest;
import java.util.List;

/**
 * Service interface for handling grants application details.
 * Provides methods for creating, updating, and retrieving application information.
 */
public interface GrantsApplicationService {

    /**
     * Retrieves a list of all application details.
     *
     * @return a list of {@link ApplicationDetailsDto} containing the details of all applications.
     */
    List<ApplicationDetailsDto> getAllApplicationDetails();
    /**
     * Creates new application details based on the provided request.
     *
     * @param applicationCreateRequest the request containing the details for the new application.
     * @return a list of {@link ApplicationDetailsDto} containing the created application details.
     */
    List<ApplicationDetailsDto> createApplicationDetails(ApplicationCreateRequest applicationCreateRequest);
    /**
     * Retrieves a list of application details based on the provided application ID.
     *
     * @param applicationId the ID of the application whose details are to be retrieved.
     * @return a list of {@link ApplicationDetailsDto} containing the details of the application.
     */
    List<ApplicationDetailsDto> getAllApplicationDetailsByApplicationId(String applicationId);
    /**
     * Updates the details of an existing application based on the provided request.
     *
     * @param applicationUpdateRequest the request containing the updated details for the application.
     * @return a list of {@link ApplicationDetailsDto} containing the updated application details.
     */
    List<ApplicationDetailsDto> updateApplicationDetails(ApplicationUpdateRequest applicationUpdateRequest);

}
