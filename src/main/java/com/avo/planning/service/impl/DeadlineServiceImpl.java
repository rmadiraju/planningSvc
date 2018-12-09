package com.avo.planning.service.impl;

import com.avo.planning.domain.Deadline;
import com.avo.planning.repository.CampaignRepository;
import com.avo.planning.repository.DeadlineRepository;
import com.avo.planning.service.DeadlineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Campaign.
 */
@Service
@SuppressWarnings(value = "DuplicateCode")
public class DeadlineServiceImpl extends AbstractServiceImpl implements DeadlineService {

    private final Logger log = LoggerFactory.getLogger(DeadlineServiceImpl.class);

    private final DeadlineRepository deadlineRepository;

    public DeadlineServiceImpl(DeadlineRepository deadlineRepository) {
        this.deadlineRepository = deadlineRepository;
    }

    /**
     * Save a campaign.
     *
     * @param campaign the entity to save
     * @return the persisted entity
     */
    @Override
    public Deadline save(Deadline deadline) {

        if (deadline.getDeadlineType() != null )
            deadline.getDeadlineType().setId(deadlineTypeService.save(deadline.getDeadlineType()).getId());

        return deadlineRepository.save(deadline);

    }

    /**
     * Get all the deadlines.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    public Page<Deadline> findAll(Pageable pageable) {
        log.debug("Request to get all Deadlines");
        return deadlineRepository.findAll(pageable);
    }


    /**
     * Get one campaign by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<Deadline> findOne(String id) {
        log.debug("Request to get Campaign : {}", id);
        return deadlineRepository.findById(id);
    }

    /**
     * Delete the campaign by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Deadline : {}", id);
        deadlineRepository.deleteById(id);
    }


    @Override
    public Optional<Deadline> findByName(String name) {
        log.debug("Request to get Deadline by Name : {}", name);
        return deadlineRepository.findByName(name);
    }

}
