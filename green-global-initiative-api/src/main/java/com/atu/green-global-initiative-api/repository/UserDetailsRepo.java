package com.atu.devops.repository;

import com.atu.devops.model.dao.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserDetailsRepo extends JpaRepository<UserDetails, Integer> {

//    @Query("Select u from UserDetails u")
//    List<UserDetails> findAllUserDetails();


    @Query("Select u from UserDetails u where u.email = :email")
    List<UserDetails> findAllByEmail(@Param("email") String email);
}
