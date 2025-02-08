package com.atu.green_global_initiative_api.service;

import com.atu.green_global_initiative_api.dto.ApplicationDetailsDto;
import com.atu.green_global_initiative_api.model.dao.ApplicationDetails;
import com.atu.green_global_initiative_api.model.dao.Grants;
import com.atu.green_global_initiative_api.model.dao.UserDetails;
import com.atu.green_global_initiative_api.model.dao.request.ApplicationUpdateRequest;
import com.atu.green_global_initiative_api.repository.ApplicationDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GrantsApplicationServiceImpl implements GrantsApplicationService {

    @Autowired
    ApplicationDetailsRepo applicationDetailsRepo;

    @Override
    public List<ApplicationDetailsDto> getAllApplicationDetails() {
        List<ApplicationDetails> applicationDetailsList = applicationDetailsRepo.findAll();
        return mapToApplicationDetailsDto(applicationDetailsList);
    }

    private static List<ApplicationDetailsDto> mapToApplicationDetailsDto(List<ApplicationDetails> applicationDetailsList) {
        List<ApplicationDetailsDto> applicationDetailsDtoList = new ArrayList<ApplicationDetailsDto>();
        for (ApplicationDetails applicationDetails : applicationDetailsList) {
            ApplicationDetailsDto applicationDetailsDto = new ApplicationDetailsDto();
            applicationDetailsDto.setApplicationId(applicationDetails.getApplicationId());
            applicationDetailsDto.setOrganizationName(applicationDetails.getOrganizationName());
            applicationDetailsDto.setApplicationStatus(applicationDetails.getApplicationStatus());
            applicationDetailsDto.setApprovalDate(applicationDetails.getApprovalDate());

            Grants grants = new Grants();
            grants.setGrantId(applicationDetails.getGrants().getGrantId());
            grants.setGrantName(applicationDetails.getGrants().getGrantName());
            grants.setDescription(applicationDetails.getGrants().getDescription());
            grants.setAmount(applicationDetails.getGrants().getAmount());
            grants.setEligibility(applicationDetails.getGrants().getEligibility());
            applicationDetailsDto.setGrants(grants);

            UserDetails userDetails = new UserDetails();
            userDetails.setUserId(applicationDetails.getUserDetails().getUserId());
            userDetails.setEmail(applicationDetails.getUserDetails().getEmail());
            userDetails.setFirstName(applicationDetails.getUserDetails().getFirstName());
            userDetails.setLastName(applicationDetails.getUserDetails().getLastName());
            applicationDetailsDto.setUserDetails(userDetails);

            applicationDetailsDtoList.add(applicationDetailsDto);
        }

        return applicationDetailsDtoList;
    }

    @Override
    public List<ApplicationDetailsDto> getAllApplicationDetailsByApplicationId(String applicationId) {
        List<ApplicationDetails> applicationDetailsList = applicationDetailsRepo.findAllByApplicationId(applicationId);
        List<ApplicationDetailsDto> applicationDetailsDtoList = mapToApplicationDetailsDto(applicationDetailsList);
        return applicationDetailsDtoList;
    }

    @Override
    public ApplicationDetailsDto updateApplicationDetails(ApplicationUpdateRequest applicationUpdateRequest) {
        return null;
    }
}
