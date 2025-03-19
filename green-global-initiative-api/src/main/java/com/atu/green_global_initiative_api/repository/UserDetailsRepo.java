package com.atu.green_global_initiative_api.repository;

import com.atu.green_global_initiative_api.model.dao.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository interface for interacting with the UserDetails entity in the database.
 * Provides methods to fetch user details based on email or userId.
 */
@Repository
public interface UserDetailsRepo extends JpaRepository<UserDetails, Integer> {

    /**
     * Finds all user details by email.
     *
     * @param email The email address of the user.
     * @return A list of {@link UserDetails} objects associated with the provided email.
     */
    @Query("Select u from UserDetails u where u.email = :email")
    List<UserDetails> findAllByEmail(@Param("email") String email);

    /**
     * Finds all user details by userId.
     *
     * @param userId The userId of the user.
     * @return A list of {@link UserDetails} objects associated with the provided userId.
     */
    @Query("Select u from UserDetails u where u.userId = :id")
    List<UserDetails> findAllById(@Param("id") Integer userId);
}
