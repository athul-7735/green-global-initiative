package com.atu.green_global_initiative_api.repository;

import com.atu.green_global_initiative_api.model.dao.ContactUs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactUsRepo extends JpaRepository<ContactUs, Integer> {

}
