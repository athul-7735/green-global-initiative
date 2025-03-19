package com.atu.green_global_initiative_api.repository;

import com.atu.green_global_initiative_api.model.dao.Grants;
import com.atu.green_global_initiative_api.model.dao.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository interface for interacting with the 'Grants' entity in the database.
 * Provides methods for performing CRUD operations and custom queries related to grants.
 */
@Repository
public interface GrantsRepo extends JpaRepository<Grants, Integer> {
    /**
     * Retrieves a list of Grants entities based on the provided grant ID.
     *
     * @param grantId the ID of the grant to search for.
     * @return a list of Grants entities with the specified grant ID.
     */
    @Query("Select u from Grants u where u.grantId = :id")
    List<Grants> findAllById(@Param("id") Integer grantId);
}
