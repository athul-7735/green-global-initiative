package com.atu.green_global_initiative_api.repository;

import com.atu.green_global_initiative_api.model.dao.ApplicationDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository interface for performing CRUD operations on {@link ApplicationDetails}.
 * Extends JpaRepository to leverage built-in methods for interacting with the database.
 */
@Repository
public interface ApplicationDetailsRepo extends JpaRepository<ApplicationDetails, Integer> {
    /**
     * Finds all {@link ApplicationDetails} records with the given application ID.
     *
     * @param applicationId the ID of the application to search for.
     * @return a list of {@link ApplicationDetails} matching the provided application ID.
     */
    @Query("SELECT a FROM ApplicationDetails a WHERE a.applicationId = :applicationId")
    List<ApplicationDetails> findAllByApplicationId(@Param("applicationId") Integer applicationId);
}
