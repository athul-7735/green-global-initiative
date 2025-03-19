package com.atu.green_global_initiative_api.service;

import com.atu.green_global_initiative_api.dto.GrantsDto;
import com.atu.green_global_initiative_api.model.dao.Grants;
import com.atu.green_global_initiative_api.repository.GrantsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * Service implementation for handling operations related to grants.
 */
@Service
public class GrantsServiceImpl implements GrantsService {

    @Autowired
    private GrantsRepo grantsRepo;

    /**
     * Retrieves a list of all grants available in the system.
     *
     * @return a list of {@link GrantsDto} containing details of all grants.
     */
    @Override
    public List<GrantsDto> getAllGrants() {
        List<Grants> grantsList = grantsRepo.findAll();
        List<GrantsDto> grantsDtoList = new ArrayList<>();
        // Map each Grants entity to its corresponding DTO
        grantsList.stream().forEach(grants -> {
            GrantsDto grant = new GrantsDto();
            grant.setGrantId(grants.getGrantId());
            grant.setGrantName(grants.getGrantName());
            grant.setAmount(grants.getAmount());
            grant.setDescription(grants.getDescription());
            grant.setEligibility(grants.getEligibility());
            grantsDtoList.add(grant);
        });
        return grantsDtoList;
    }
}
