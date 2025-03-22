package com.atu.green_global_initiative_api.controller;

import com.atu.green_global_initiative_api.NausicaaGreenInitiativeApplication;
import com.atu.green_global_initiative_api.model.dao.ContactUs;
import com.atu.green_global_initiative_api.service.QueryServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing "Contact Us" queries.
 * Provides an endpoint for users to submit their queries or feedback.
 */
@RestController
@RequestMapping("/api/contact-us")
@CrossOrigin(origins = "*")
public class QueryController {

    @Autowired
    QueryServiceImpl queryService;

    static final Logger logger = LoggerFactory.getLogger(NausicaaGreenInitiativeApplication.class);
    /**
     * Creates a new query or feedback from a user.
     *
     * @param contactUs The details of the query or feedback submitted by the user.
     * @return The saved {@link ContactUs} object containing the query details.
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
