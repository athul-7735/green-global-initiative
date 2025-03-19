package com.atu.green_global_initiative_api.repository;

import com.atu.green_global_initiative_api.model.dao.Grants;
import com.atu.green_global_initiative_api.model.dao.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrantsRepo extends JpaRepository<Grants, Integer> {

    @Query("Select u from Grants u where u.grantId = :id")
    List<Grants> findAllById(@Param("id") Integer grantId);
}
