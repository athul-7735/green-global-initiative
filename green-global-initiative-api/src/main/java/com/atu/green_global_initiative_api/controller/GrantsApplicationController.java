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
 * REST controller for managing grant applications for the Nausicaä Green Global Initiative.
 * Provides endpoints for CRUD operations on grant applications.
 */
@RestController
@RequestMapping("/api/applications")
@CrossOrigin(origins = "*")
public class GrantsApplicationController {

    @Autowired
    private GrantsApplicationServiceImpl grantsApplicationService;

    static final Logger logger = LoggerFactory.getLogger(NausicaaGreenInitiativeApplication.class);
    /**
     * Retrieves all grant applications.
     *
     * @return A list of all {@link ApplicationDetailsDto} objects.
     */
    // Get all users
    @GetMapping
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<ApplicationDetailsDto>> getAllApplications() {
        List<ApplicationDetailsDto> applicationDetailsList = grantsApplicationService.getAllApplicationDetails();
        return new ResponseEntity<>(applicationDetailsList, HttpStatus.OK);
    }
    /**
     * Retrieves a specific grant application by its ID.
     *
     * @param id The ID of the grant application to retrieve.
     * @return A list of {@link ApplicationDetailsDto} objects for the specified ID.
     * @throws IllegalArgumentException If the ID is invalid.
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
     * @param applicationCreateRequest The details of the application to create.
     * @return The created {@link ApplicationDetailsDto} object.
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
     * @param applicationUpdateRequest The details of the application to update.
     * @return The updated {@link ApplicationDetailsDto} object, or an error response if the update fails.
     */
    @PatchMapping
    @CrossOrigin(origins = "*")
    public ResponseEntity<ApplicationDetailsDto> updateApplication(@RequestBody ApplicationUpdateRequest applicationUpdateRequest) {
        logger.info("patchApplication method Started");
        List<ApplicationDetailsDto> res = new ArrayList<>();
        try {
            // Validate the application status
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
        if(!res.isEmpty()){
            return ResponseEntity.ok(res.getFirst());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
