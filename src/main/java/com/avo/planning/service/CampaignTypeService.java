package com.avo.planning.service;

import com.avo.planning.domain.CampaignType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing CampaignType.
 */
public interface CampaignTypeService {

    /**
     * Save a campaignType.
     *
     * @param campaignType the entity to save
     * @return the persisted entity
     */
    CampaignType save(CampaignType campaignType);

    /**
     * Get all the campaignTypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CampaignType> findAll(Pageable pageable);


    /**
     * Get the "id" campaignType.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CampaignType> findOne(String id);

    /**
     * Delete the "id" campaignType.
     *
     * @param id the id of the entity
     */
    void delete(String id);
}
