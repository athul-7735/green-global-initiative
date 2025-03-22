package com.atu.green_global_initiative_api.repository;

import com.atu.green_global_initiative_api.model.dao.ApplicationDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository interface for managing {@link ApplicationDetails} entities.
 * <p>This interface extends {@link JpaRepository}, providing basic CRUD operations on the "ApplicationDetails" table.
 * It also defines custom query methods for retrieving application details by application ID.</p>
 *
 * <p>Custom queries in this interface are written using JPQL (Java Persistence Query Language) for flexibility in querying
 * the database beyond the basic CRUD operations provided by {@link JpaRepository}.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * List&lt;ApplicationDetails&gt; details = applicationDetailsRepo.findAllByApplicationId(123);
 * </pre>
 *
 * @author Name Here
 * @since 2025-03-19
 */
@Repository
public interface ApplicationDetailsRepo extends JpaRepository<ApplicationDetails, Integer> {

    /**
     * Retrieves a list of {@link ApplicationDetails} entities based on the given application ID.
     *
     * @param applicationId the application ID used to filter the application details.
     * @return a list of {@link ApplicationDetails} that match the given application ID.
     */
    @Query("SELECT a FROM ApplicationDetails a WHERE a.applicationId = :applicationId")
    List<ApplicationDetails> findAllByApplicationId(@Param("applicationId") Integer applicationId);
}
