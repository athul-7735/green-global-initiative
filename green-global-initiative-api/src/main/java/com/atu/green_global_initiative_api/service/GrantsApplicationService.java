package com.atu.green_global_initiative_api.service;


import com.atu.green_global_initiative_api.dto.ApplicationDetailsDto;
import com.atu.green_global_initiative_api.model.dao.request.ApplicationCreateRequest;
import com.atu.green_global_initiative_api.model.dao.request.ApplicationUpdateRequest;
import java.util.List;

/**
 * Service interface for managing grants applications.
 * This interface defines methods to create, update, and retrieve application details.
 */
public interface GrantsApplicationService {

    /**
     * Retrieves a list of all application details.
     *
     * @return a list of {@link ApplicationDetailsDto} containing details of all applications.
     */
    List<ApplicationDetailsDto> getAllApplicationDetails();

    /**
     * Creates a new application based on the provided request.
     *
     * @param applicationCreateRequest the request containing data to create a new application.
     * @return a list of {@link ApplicationDetailsDto} representing the created application(s).
     */
    List<ApplicationDetailsDto> createApplicationDetails(ApplicationCreateRequest applicationCreateRequest);

    /**
     * Retrieves the details of an application based on the given application ID.
     *
     * @param applicationId the ID of the application whose details are to be retrieved.
     * @return a list of {@link ApplicationDetailsDto} containing the details of the specified application.
     */
    List<ApplicationDetailsDto> getAllApplicationDetailsByApplicationId(String applicationId);

    /**
     * Updates an existing application based on the provided update request.
     *
     * @param applicationUpdateRequest the request containing the updated details of the application.
     * @return a list of {@link ApplicationDetailsDto} representing the updated application(s).
     */
    List<ApplicationDetailsDto> updateApplicationDetails(ApplicationUpdateRequest applicationUpdateRequest);

}
