package com.atu.green_global_initiative_api.service;

import com.atu.green_global_initiative_api.dto.ApplicationDetailsDto;
import com.atu.green_global_initiative_api.model.dao.ContactUs;
import com.atu.green_global_initiative_api.model.dao.request.ApplicationCreateRequest;
import com.atu.green_global_initiative_api.model.dao.request.ApplicationUpdateRequest;
import com.atu.green_global_initiative_api.model.dao.request.QueryCreateRequest;

import java.util.List;

public interface QueryService {
    List<ContactUs> getAllQueries();
    ContactUs createQuery(ContactUs contactUs);
    List<ContactUs> getQueryById(String queryId);
}
