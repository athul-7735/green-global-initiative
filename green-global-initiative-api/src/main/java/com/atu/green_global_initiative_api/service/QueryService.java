package com.atu.green_global_initiative_api.service;

import com.atu.green_global_initiative_api.dto.ApplicationDetailsDto;
import com.atu.green_global_initiative_api.model.dao.ContactUs;
import com.atu.green_global_initiative_api.model.dao.request.ApplicationCreateRequest;
import com.atu.green_global_initiative_api.model.dao.request.ApplicationUpdateRequest;
import com.atu.green_global_initiative_api.model.dao.request.QueryCreateRequest;
import java.util.List;


/**
 * Service interface for handling operations related to user queries.
 */
public interface QueryService {
    /**
     * Retrieves a list of all user queries in the system.
     *
     * @return a list of {@link ContactUs} containing details of all queries.
     */
    List<ContactUs> getAllQueries();
    /**
     * Creates a new user query in the system.
     *
     * @param contactUs the {@link ContactUs} object containing details of the query to be created.
     * @return the created {@link ContactUs} object.
     */
    ContactUs createQuery(ContactUs contactUs);
    /**
     * Retrieves a specific query by its unique identifier.
     *
     * @param queryId the unique identifier of the query.
     * @return a list of {@link ContactUs} containing the details of the specified query.
     */
    List<ContactUs> getQueryById(String queryId);
}
