package com.atu.green_global_initiative_api.controller;

import com.atu.green_global_initiative_api.NausicaaGreenInitiativeApplication;
import com.atu.green_global_initiative_api.model.dao.ContactUs;
import com.atu.green_global_initiative_api.service.QueryServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact-us")
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "*")
public class QueryController {

    @Autowired
    QueryServiceImpl queryService;

    static final Logger logger = LoggerFactory.getLogger(NausicaaGreenInitiativeApplication.class);

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
