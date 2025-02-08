package com.atu.green_global_initiative_api.service;


import com.atu.green_global_initiative_api.dto.ApplicationDetailsDto;
import com.atu.green_global_initiative_api.model.dao.ApplicationDetails;
import com.atu.green_global_initiative_api.model.dao.request.ApplicationUpdateRequest;

import java.util.List;

public interface GrantsApplicationService {
    List<ApplicationDetailsDto> getAllApplicationDetails();
    List<ApplicationDetailsDto> getAllApplicationDetailsByApplicationId(String applicationId);
    ApplicationDetailsDto updateApplicationDetails(ApplicationUpdateRequest applicationUpdateRequest);
}
