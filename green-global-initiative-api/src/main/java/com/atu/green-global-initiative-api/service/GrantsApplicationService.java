package com.atu.devops.service;


import com.atu.devops.dto.ApplicationDetailsDto;
import com.atu.devops.model.dao.ApplicationDetails;
import com.atu.devops.model.dao.request.ApplicationUpdateRequest;

import java.util.List;

public interface GrantsApplicationService {
    List<ApplicationDetailsDto> getAllApplicationDetails();
    List<ApplicationDetailsDto> getAllApplicationDetailsByApplicationId(String applicationId);
    ApplicationDetailsDto updateApplicationDetails(ApplicationUpdateRequest applicationUpdateRequest);
}
