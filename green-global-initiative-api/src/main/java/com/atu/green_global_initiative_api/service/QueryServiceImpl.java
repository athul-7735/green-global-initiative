package com.atu.green_global_initiative_api.service;

import com.atu.green_global_initiative_api.model.dao.ContactUs;
import com.atu.green_global_initiative_api.model.dao.request.QueryCreateRequest;
import com.atu.green_global_initiative_api.repository.ContactUsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the QueryService interface.
 * Provides methods for managing user-submitted queries in the "Contact Us" form.
 * This service interacts with the repository to persist and retrieve query data.
 */
@Service
public class QueryServiceImpl implements QueryService{

    @Autowired
    ContactUsRepo contactUsRepo;

    /**
     * Retrieves all submitted queries.
     *
     * @return an empty list, as the method is currently not implemented to retrieve actual data.
     */
    @Override
    public List<ContactUs> getAllQueries() {
        return List.of();
    }

    /**
     * Creates and saves a new query submitted by the user.
     *
     * @param contactUs the ContactUs object containing the details of the query to be created.
     * @return the created ContactUs object after it has been saved to the repository.
     */
    @Override
    public ContactUs createQuery(ContactUs contactUs) {
        List<ContactUs> contactUsList = new ArrayList<>();
//        ContactUs contactUs = new ContactUs();
//        contactUs.setQueryId(queryCreateRequest.getQueryId());
//        contactUs.setName(queryCreateRequest.getName());
//        contactUs.setEmail(queryCreateRequest.getEmail());
//        contactUs.setPhone(queryCreateRequest.getPhone());
//        contactUs.setMessage(queryCreateRequest.getMessage());
        contactUsList.add(contactUs); // Adds the query to a temporary list (not used further)
        return contactUsRepo.save(contactUs); // Saves the query to the database
    }

    /**
     * Retrieves a list of queries based on the provided query ID.
     *
     * @param queryId the unique identifier of the query to be retrieved.
     * @return an empty list, as the method is currently not implemented to retrieve actual data.
     */
    @Override
    public List<ContactUs> getQueryById(String queryId) {
        return List.of(); // Returns an empty list for now
    }
}
