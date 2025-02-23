package com.atu.green_global_initiative_api.controller;

import com.atu.green_global_initiative_api.NausicaaGreenInitiativeApplication;
import com.atu.green_global_initiative_api.dto.ApplicationDetailsDto;
import com.atu.green_global_initiative_api.dto.UserDetailsDto;
import com.atu.green_global_initiative_api.model.dao.ApplicationDetails;
import com.atu.green_global_initiative_api.model.dao.UserDetails;
import com.atu.green_global_initiative_api.model.dao.request.ApplicationCreateRequest;
import com.atu.green_global_initiative_api.service.GrantsApplicationServiceImpl;
import com.atu.green_global_initiative_api.service.UserServiceImpl;
import io.micrometer.core.ipc.http.HttpSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin(origins = "*")
public class GrantsApplicationController {

    @Autowired
    private GrantsApplicationServiceImpl grantsApplicationService;

    static final Logger logger = LoggerFactory.getLogger(NausicaaGreenInitiativeApplication.class);

    // Get all users
    @GetMapping
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<ApplicationDetailsDto>> getAllApplications() {
        List<ApplicationDetailsDto> applicationDetailsList = grantsApplicationService.getAllApplicationDetails();
        return new ResponseEntity<>(applicationDetailsList, HttpStatus.OK);
    }

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

}
