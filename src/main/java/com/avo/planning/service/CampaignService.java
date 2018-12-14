package com.avo.planning.service;

import com.avo.planning.domain.Calendar;
import com.avo.planning.domain.Campaign;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Campaign.
 */
public interface CampaignService {

    /**
     * Save a campaign.
     *
     * @param campaign the entity to save
     * @return the persisted entity
     */
    Campaign save(Campaign campaign);

    /**
     * Get all the campaigns.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Campaign> findAll(Pageable pageable);


    /**
     * Get the "id" campaign.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Campaign> findOne(String id);

    /**
     * Delete the "id" campaign.
     *
     * @param id the id of the entity
     */
    void delete(String id);

    /**
     * Get all the campaigns by Calendar
     *
     * @param Campaign
     * @return the list of entities
     */

    List<Campaign> findCampaignsByCalendar(String calendar);

    Optional<Campaign> findByName(String name) ;

    List<Campaign> getCampaignTemplates();

}
