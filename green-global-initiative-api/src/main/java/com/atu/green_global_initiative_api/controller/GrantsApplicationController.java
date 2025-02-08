package com.atu.green_global_initiative_api.controller;

import com.atu.green_global_initiative_api.dto.ApplicationDetailsDto;
import com.atu.green_global_initiative_api.model.dao.ApplicationDetails;
import com.atu.green_global_initiative_api.model.dao.UserDetails;
import com.atu.green_global_initiative_api.service.GrantsApplicationServiceImpl;
import com.atu.green_global_initiative_api.service.UserServiceImpl;
import io.micrometer.core.ipc.http.HttpSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class GrantsApplicationController {

    @Autowired
    private GrantsApplicationServiceImpl grantsApplicationService;

    // Get all users
    @GetMapping
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<ApplicationDetailsDto>> getAllApplications() {
        List<ApplicationDetailsDto> applicationDetailsList = grantsApplicationService.getAllApplicationDetails();
        return new ResponseEntity<>(applicationDetailsList, HttpStatus.OK);
    }


}
