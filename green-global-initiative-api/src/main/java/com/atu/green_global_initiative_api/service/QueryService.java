package com.atu.green_global_initiative_api.service;

import com.atu.green_global_initiative_api.dto.ApplicationDetailsDto;
import com.atu.green_global_initiative_api.model.dao.ContactUs;
import com.atu.green_global_initiative_api.model.dao.request.ApplicationCreateRequest;
import com.atu.green_global_initiative_api.model.dao.request.ApplicationUpdateRequest;
import com.atu.green_global_initiative_api.model.dao.request.QueryCreateRequest;
import java.util.List;

/**
 * Service interface for handling queries related to contact requests.
 * Provides methods for creating, retrieving, and managing queries submitted
 * by users through the "Contact Us" form.
 */
public interface QueryService {

    /**
     * Retrieves a list of all queries that have been submitted.
     *
     * @return a list of ContactUs objects representing all submitted queries.
     */
    List<ContactUs> getAllQueries();

    /**
     * Creates a new query submitted by a user.
     *
     * @param contactUs the ContactUs object containing the details of the query to be created.
     * @return the created ContactUs object after it has been saved to the database.
     */
    ContactUs createQuery(ContactUs contactUs);

    /**
     * Retrieves a list of queries that match the provided query ID.
     *
     * @param queryId the unique identifier of the query to be retrieved.
     * @return a list of ContactUs objects that match the specified query ID.
     */
    List<ContactUs> getQueryById(String queryId);
}
