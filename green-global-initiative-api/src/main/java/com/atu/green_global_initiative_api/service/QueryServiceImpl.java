package com.atu.green_global_initiative_api.service;

import com.atu.green_global_initiative_api.model.dao.ContactUs;
import com.atu.green_global_initiative_api.model.dao.request.QueryCreateRequest;
import com.atu.green_global_initiative_api.repository.ContactUsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service implementation for handling operations related to user queries.
 */
@Service
public class QueryServiceImpl implements QueryService{

    @Autowired
    ContactUsRepo contactUsRepo;


    /**
     * Retrieves all user queries from the system.
     *
     * @return an empty list of {@link ContactUs}, as the implementation is currently incomplete.
     */
    @Override
    public List<ContactUs> getAllQueries() {
        return List.of();
    }

    /**
     * Creates a new query in the system.
     *
     * @param contactUs the {@link ContactUs} object containing the details of the query to be created.
     * @return the created {@link ContactUs} object after saving it to the database.
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
        contactUsList.add(contactUs);
        return contactUsRepo.save(contactUs);
    }
    /**
     * Retrieves a specific query by its unique identifier.
     *
     * @param queryId the unique identifier of the query.
     * @return an empty list of {@link ContactUs}, as the implementation is currently incomplete.
     */
    @Override
    public List<ContactUs> getQueryById(String queryId) {
        return List.of();
    }
}
