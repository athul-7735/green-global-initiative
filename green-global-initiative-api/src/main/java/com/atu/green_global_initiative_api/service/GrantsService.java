package com.atu.green_global_initiative_api.service;

import com.atu.green_global_initiative_api.dto.GrantsDto;
import java.util.List;

/**
 * Service interface for managing grants.
 * This interface defines methods for retrieving grant information.
 */
public interface GrantsService {

    /**
     * Retrieves a list of all available grants.
     *
     * @return a list of {@link GrantsDto} containing details of all grants.
     */
    List<GrantsDto> getAllGrants();
}
