package com.atu.green_global_initiative_api.service;

import com.atu.green_global_initiative_api.dto.GrantsDto;
import com.atu.green_global_initiative_api.model.dao.Grants;
import com.atu.green_global_initiative_api.repository.GrantsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * Service implementation for handling grants-related operations.
 * This class communicates with the repository to fetch data and processes it
 * into a more suitable format (DTOs) for further use, such as in controllers.
 */
@Service
public class GrantsServiceImpl implements GrantsService {

    @Autowired
    private GrantsRepo grantsRepo;

    /**
     * Retrieves all available grants from the repository and converts them into
     * DTOs to be returned to the caller.
     *
     * @return a list of GrantsDto objects representing all grants.
     */
    @Override
    public List<GrantsDto> getAllGrants() {

        // Fetch all grants from the database using the repository
        List<Grants> grantsList = grantsRepo.findAll();

        // Initialize a list to store converted GrantsDto objects
        List<GrantsDto> grantsDtoList = new ArrayList<>();

        // Convert each Grants entity to a GrantsDto object
        grantsList.stream().forEach(grants -> {
            GrantsDto grant = new GrantsDto();
            grant.setGrantId(grants.getGrantId());
            grant.setGrantName(grants.getGrantName());
            grant.setAmount(grants.getAmount());
            grant.setDescription(grants.getDescription());
            grant.setEligibility(grants.getEligibility());
            grantsDtoList.add(grant);
        });

        // Return the list of GrantsDto objects
        return grantsDtoList;
    }
}
