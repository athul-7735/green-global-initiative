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

@RestController
@RequestMapping("/api/grants")
@CrossOrigin(origins = "*")
public class GrantsController {

    @Autowired
    private GrantsServiceImpl grantsService;

    static final Logger logger = LoggerFactory.getLogger(NausicaaGreenInitiativeApplication.class);

    // Get all users
    @GetMapping
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<GrantsDto>> getAllApplications() {
        List<GrantsDto> grantsDtoList = grantsService.getAllGrants();
        return new ResponseEntity<>(grantsDtoList, HttpStatus.OK);
    }
}
