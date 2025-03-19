package com.atu.green_global_initiative_api.controller;

import com.atu.green_global_initiative_api.NausicaaGreenInitiativeApplication;
import com.atu.green_global_initiative_api.dto.ApplicationDetailsDto;
import com.atu.green_global_initiative_api.dto.GrantsDto;
import com.atu.green_global_initiative_api.model.dao.Grants;
import com.atu.green_global_initiative_api.service.GrantsApplicationServiceImpl;
import com.atu.green_global_initiative_api.service.GrantsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for managing grants in the Nausicaä Global Green Initiative API.
 * Provides endpoints to retrieve grant-related information.
 */

@RestController
@RequestMapping("/api/grants")
@CrossOrigin(origins = "*")
public class GrantsController {

    /**
     * Service layer for managing grants.
     */

    @Autowired
    private GrantsServiceImpl grantsService;

    /**
     * Logger for tracking and debugging the application's flow.
     */

    static final Logger logger = LoggerFactory.getLogger(NausicaaGreenInitiativeApplication.class);
    /**
     * Retrieves a list of all grants.
     *
     * @return a {@link ResponseEntity} containing a list of {@link GrantsDto} objects
     * representing the grants, along with an HTTP status code of 200 (OK).
     */
    // Get all users
    @GetMapping
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<GrantsDto>> getAllApplications() {
        List<GrantsDto> grantsDtoList = grantsService.getAllGrants();
        return new ResponseEntity<>(grantsDtoList, HttpStatus.OK);
    }
}
