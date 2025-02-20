package com.atu.green_global_initiative_api.repository;

import com.atu.green_global_initiative_api.model.dao.ApplicationDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationDetailsRepo extends JpaRepository<ApplicationDetails, Integer> {

    @Query("SELECT a FROM ApplicationDetails a WHERE a.applicationId = :applicationId")
    List<ApplicationDetails> findAllByApplicationId(@Param("applicationId") String applicationId);
}
