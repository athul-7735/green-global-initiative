package com.atu.green_global_initiative_api.controller;

import com.atu.green_global_initiative_api.NausicaaGreenInitiativeApplication;
import com.atu.green_global_initiative_api.model.dao.ContactUs;
import com.atu.green_global_initiative_api.service.QueryServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for handling "Contact Us" queries in the Nausicaä Global Green Initiative API.
 * Provides an endpoint for users to submit queries.
 */

@RestController
@RequestMapping("/api/contact-us")
@CrossOrigin(origins = "*")
public class QueryController {
    /**
     * Service layer for handling query-related logic.
     */
    @Autowired
    QueryServiceImpl queryService;
    /**
     * Logger for tracking and debugging the application's flow.
     */
    static final Logger logger = LoggerFactory.getLogger(NausicaaGreenInitiativeApplication.class);
    /**
     * Submits a "Contact Us" query.
     *
     * @param contactUs the {@link ContactUs} object containing the details of the user's query.
     * @return the created {@link ContactUs} object after being saved.
     */
    @PostMapping()
    public ContactUs createQuery(@RequestBody ContactUs contactUs) {
        logger.info("createQuery method Started");
        ContactUs res = new ContactUs();
        try {
            res = queryService.createQuery(contactUs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
