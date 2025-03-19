package com.atu.green_global_initiative_api.repository;

import com.atu.green_global_initiative_api.model.dao.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository interface for managing {@link UserDetails} entities.
 * <p>This interface extends {@link JpaRepository}, providing basic CRUD operations on the "UserDetails" table.
 * It also defines custom query methods for retrieving user details based on specific criteria, such as email or user ID.</p>
 *
 * <p>Custom queries in this interface are written using JPQL (Java Persistence Query Language) to allow fetching user details
 * using different attributes, beyond the standard CRUD operations provided by {@link JpaRepository}.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * List<UserDetails> usersByEmail = userDetailsRepo.findAllByEmail("user@example.com");
 * </pre>
 *
 * @author Name Here
 * @since 2025-03-19
 */
@Repository
public interface UserDetailsRepo extends JpaRepository<UserDetails, Integer> {

    /**
     * Retrieves a list of {@link UserDetails} entities based on the given email.
     *
     * @param email the email used to filter the user details.
     * @return a list of {@link UserDetails} entities matching the given email.
     */
    @Query("Select u from UserDetails u where u.email = :email")
    List<UserDetails> findAllByEmail(@Param("email") String email);

    /**
     * Retrieves a list of {@link UserDetails} entities based on the given user ID.
     *
     * @param userId the ID of the user to filter the results.
     * @return a list of {@link UserDetails} entities matching the given user ID.
     */
    @Query("Select u from UserDetails u where u.userId = :id")
    List<UserDetails> findAllById(@Param("id") Integer userId);
}
