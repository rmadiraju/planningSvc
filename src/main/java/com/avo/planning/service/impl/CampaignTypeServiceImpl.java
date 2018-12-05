package com.avo.planning.service.impl;

import com.avo.planning.domain.CampaignType;
import com.avo.planning.repository.CampaignTypeRepository;
import com.avo.planning.service.CampaignTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing CampaignType.
 */
@Service
public class CampaignTypeServiceImpl implements CampaignTypeService {

    private final Logger log = LoggerFactory.getLogger(CampaignTypeServiceImpl.class);

    private final CampaignTypeRepository campaignTypeRepository;

    public CampaignTypeServiceImpl(CampaignTypeRepository campaignTypeRepository) {
        this.campaignTypeRepository = campaignTypeRepository;
    }

    /**
     * Save a campaignType.
     *
     * @param campaignType the entity to save
     * @return the persisted entity
     */
    @Override
    public CampaignType save(CampaignType campaignType) {
        log.debug("Request to save CampaignType : {}", campaignType);

        CampaignType campType = campaignTypeRepository.findFirstByNameAndType(campaignType.getName(), campaignType.getType());
        if (campType != null)
            campaignType.setId(campType.getId());

        return campaignTypeRepository.save(campaignType);
    }

    /**
     * Get all the campaignTypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    public Page<CampaignType> findAll(Pageable pageable) {
        log.debug("Request to get all CampaignTypes");
        return campaignTypeRepository.findAll(pageable);
    }


    /**
     * Get one campaignType by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<CampaignType> findOne(String id) {
        log.debug("Request to get CampaignType : {}", id);
        return campaignTypeRepository.findById(id);
    }

    /**
     * Delete the campaignType by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete CampaignType : {}", id);
        campaignTypeRepository.deleteById(id);
    }
}
