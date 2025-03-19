package com.atu.green_global_initiative_api.controller;

import com.atu.green_global_initiative_api.NausicaaGreenInitiativeApplication;
import com.atu.green_global_initiative_api.dto.ApplicationDetailsDto;
import com.atu.green_global_initiative_api.model.dao.request.ApplicationCreateRequest;
import com.atu.green_global_initiative_api.model.dao.request.ApplicationUpdateRequest;
import com.atu.green_global_initiative_api.service.GrantsApplicationServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * REST controller for managing grants applications.
 * Provides endpoints to create, retrieve, and update application details.
 */

@RestController
@RequestMapping("/api/applications")
@CrossOrigin(origins = "*")
public class GrantsApplicationController {

    /**
     * Service layer for managing grants application business logic.
     */

    @Autowired
    private GrantsApplicationServiceImpl grantsApplicationService;
    /**
     * Logger for the controller.
     */
    static final Logger logger = LoggerFactory.getLogger(NausicaaGreenInitiativeApplication.class);
    /**
     * Retrieves all grants applications.
     *
     * @return a list of {@link ApplicationDetailsDto} containing all application details.
     */
    // Get all users
    @GetMapping
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<ApplicationDetailsDto>> getAllApplications() {
        List<ApplicationDetailsDto> applicationDetailsList = grantsApplicationService.getAllApplicationDetails();
        return new ResponseEntity<>(applicationDetailsList, HttpStatus.OK);
    }
    /**
     * Retrieves a specific application by its ID.
     *
     * @param id the ID of the application to retrieve.
     * @return a list of {@link ApplicationDetailsDto} containing the application details.
     * @throws IllegalArgumentException if the ID is invalid.
     */
    @GetMapping("/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<ApplicationDetailsDto>> getApplicationById(@PathVariable String id) throws IllegalArgumentException{
        List<ApplicationDetailsDto> applicationDetailsList = grantsApplicationService.getAllApplicationDetailsByApplicationId(id);
        return new ResponseEntity<>(applicationDetailsList, HttpStatus.OK);
    }
    /**
     * Creates a new grant application.
     *
     * @param applicationCreateRequest the request containing details of the application to create.
     * @return the created {@link ApplicationDetailsDto}.
     */
    @PostMapping
    @CrossOrigin(origins = "*")
    public ApplicationDetailsDto createApplication(@RequestBody ApplicationCreateRequest applicationCreateRequest) {
        logger.info("createApplication method Started");
        List<ApplicationDetailsDto> res = new ArrayList<>();
        try {
            res = grantsApplicationService.createApplicationDetails(applicationCreateRequest);
        } catch (Exception e){
            e.printStackTrace();
        }
        return res.getFirst();
    }
    /**
     * Updates an existing grant application.
     *
     * @param applicationUpdateRequest the request containing updated application details.
     * @return a {@link ResponseEntity} containing the updated {@link ApplicationDetailsDto} or an appropriate error status.
     */
    @PatchMapping
    @CrossOrigin(origins = "*")
    public ResponseEntity<ApplicationDetailsDto> updateApplication(@RequestBody ApplicationUpdateRequest applicationUpdateRequest) {
        logger.info("patchApplication method Started");
        List<ApplicationDetailsDto> res = new ArrayList<>();
        try {
            // Validate application status
            if(Objects.equals(applicationUpdateRequest.getApplicationStatus(), "Approved") || Objects.equals(applicationUpdateRequest.getApplicationStatus(), "Rejected") || Objects.equals(applicationUpdateRequest.getApplicationStatus(), "In Progress")){
                res = grantsApplicationService.updateApplicationDetails(applicationUpdateRequest);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            if(res == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        // Return updated application details
        if(!res.isEmpty()){
            return ResponseEntity.ok(res.getFirst()); // Updated to use get(0) instead of getFirst()
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
