package com.atu.green_global_initiative_api.service;

import com.atu.green_global_initiative_api.model.dao.ContactUs;
import com.atu.green_global_initiative_api.model.dao.request.QueryCreateRequest;
import com.atu.green_global_initiative_api.repository.ContactUsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QueryServiceImpl implements QueryService{

    @Autowired
    ContactUsRepo contactUsRepo;

    @Override
    public List<ContactUs> getAllQueries() {
        return List.of();
    }

    @Override
    public ContactUs createQuery(ContactUs contactUs) {
        List<ContactUs> contactUsList = new ArrayList<>();
//        ContactUs contactUs = new ContactUs();
//        contactUs.setQueryId(queryCreateRequest.getQueryId());
//        contactUs.setName(queryCreateRequest.getName());
//        contactUs.setEmail(queryCreateRequest.getEmail());
//        contactUs.setPhone(queryCreateRequest.getPhone());
//        contactUs.setMessage(queryCreateRequest.getMessage());
        contactUsList.add(contactUs);
        return contactUsRepo.save(contactUs);
    }

    @Override
    public List<ContactUs> getQueryById(String queryId) {
        return List.of();
    }
}
