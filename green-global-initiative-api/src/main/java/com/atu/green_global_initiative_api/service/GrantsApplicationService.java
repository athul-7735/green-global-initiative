package com.atu.green_global_initiative_api.service;


import com.atu.green_global_initiative_api.dto.ApplicationDetailsDto;
import com.atu.green_global_initiative_api.model.dao.request.ApplicationCreateRequest;
import com.atu.green_global_initiative_api.model.dao.request.ApplicationUpdateRequest;

import java.util.List;

public interface GrantsApplicationService {
    List<ApplicationDetailsDto> getAllApplicationDetails();
    List<ApplicationDetailsDto> createApplicationDetails(ApplicationCreateRequest applicationCreateRequest);
    List<ApplicationDetailsDto> getAllApplicationDetailsByApplicationId(String applicationId);
    List<ApplicationDetailsDto> updateApplicationDetails(ApplicationUpdateRequest applicationUpdateRequest);

}
