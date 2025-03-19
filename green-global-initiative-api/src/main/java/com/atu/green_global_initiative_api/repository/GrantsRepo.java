package com.atu.green_global_initiative_api.repository;

import com.atu.green_global_initiative_api.model.dao.Grants;
import com.atu.green_global_initiative_api.model.dao.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository interface for managing {@link Grants} entities.
 * <p>This interface extends {@link JpaRepository}, providing basic CRUD operations on the "Grants" table.
 * It also defines custom query methods for retrieving grant information based on specific criteria.</p>
 *
 * <p>Custom queries in this interface are written using JPQL (Java Persistence Query Language) to provide more flexibility
 * in fetching grant details from the database.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * List<Grants> grants = grantsRepo.findAllById(101);
 * </pre>
 *
 * @author John Kirubaharan Ravichandran
 * @since 2025-03-19
 */
@Repository
public interface GrantsRepo extends JpaRepository<Grants, Integer> {

    /**
     * Retrieves a list of {@link Grants} entities based on the given grant ID.
     *
     * @param grantId the ID of the grant to filter the results.
     * @return a list of {@link Grants} entities matching the given grant ID.
     */
    @Query("Select u from Grants u where u.grantId = :id")
    List<Grants> findAllById(@Param("id") Integer grantId);
}
