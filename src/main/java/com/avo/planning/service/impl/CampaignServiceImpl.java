package com.avo.planning.service.impl;

import com.avo.planning.domain.Calendar;
import com.avo.planning.domain.Campaign;
import com.avo.planning.repository.AttributeRepository;
import com.avo.planning.repository.CampaignRepository;
import com.avo.planning.service.CampaignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Campaign.
 */
@Service
@SuppressWarnings(value = "DuplicateCode")
public class CampaignServiceImpl extends AbstractServiceImpl implements CampaignService {

    private final Logger log = LoggerFactory.getLogger(CampaignServiceImpl.class);

    private final CampaignRepository campaignRepository;

    public CampaignServiceImpl(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    /**
     * Save a campaign.
     *
     * @param campaign the entity to save
     * @return the persisted entity
     */
    @Override
    public Campaign save(Campaign campaign) {
        log.debug("Request to save Campaign : {}", campaign);
        campaign.getAttributes().forEach(a -> a.setId(attributeService.save(a).getId()));

        if (campaign.getSourceTemplate() != null)
            campaign.getSourceTemplate().setId(templateService.save(campaign.getSourceTemplate()).getId());

        if (campaign.getCampaignType() != null )
            campaign.getCampaignType().setId(campaignTypeService.save(campaign.getCampaignType()).getId());

        campaign.getInstruments().forEach(i->  i.setId(instrumentService.save(i).getId()));
        Campaign camp = campaignRepository.findFirstByName(campaign.getName());
        if (camp != null)
            campaign.setId(camp.getId());


        return campaignRepository.save(campaign);

    }

    /**
     * Get all the campaigns.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    public Page<Campaign> findAll(Pageable pageable) {
        log.debug("Request to get all Campaigns");
        return campaignRepository.findAll(pageable);
    }


    /**
     * Get one campaign by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<Campaign> findOne(String id) {
        log.debug("Request to get Campaign : {}", id);
        return campaignRepository.findById(id);
    }

    /**
     * Delete the campaign by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Campaign : {}", id);
        campaignRepository.deleteById(id);
    }

    @Override
    public List<Campaign> findCampaignsByCalendar(String calendar) {
        log.debug("Request to Get Campaign By Calendar : {}", calendar);
        Optional<List<Campaign>> campaigns = campaignRepository.findCampaignsByCalendar(calendar);
        if (!campaigns.isPresent()){
            return new ArrayList<>();
        }
        return campaigns.get();
    }

    @Override
    public Optional<Campaign> findByName(String name) {
        log.debug("Request to get Campaign by Name : {}", name);
        return campaignRepository.findByName(name);
    }

    @Override
    public List<Campaign> getCampaignTemplates() {
        return campaignRepository.findAll().stream().filter(campaign -> campaign.getTemplate()).collect(Collectors.toList());

    }
}
