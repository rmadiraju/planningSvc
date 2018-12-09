package com.avo.planning.service.impl;

import com.avo.planning.domain.DeadlineType;
import com.avo.planning.repository.DeadlineTypeRepository;
import com.avo.planning.service.DeadlineTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing DeadlineType.
 */
@Service
public class DeadlineTypeServiceImpl implements DeadlineTypeService {

    private final Logger log = LoggerFactory.getLogger(DeadlineTypeServiceImpl.class);

    private final DeadlineTypeRepository deadlineTypeRepository;

    public DeadlineTypeServiceImpl(DeadlineTypeRepository deadlineTypeRepository) {
        this.deadlineTypeRepository = deadlineTypeRepository;
    }

    /**
     * Save a DeadlineType.
     *
     * @param DeadlineType the entity to save
     * @return the persisted entity
     */
    @Override
    public DeadlineType save(DeadlineType deadlineType) {
        log.debug("Request to save DeadlineType : {}", deadlineType);

        DeadlineType ddlType = deadlineTypeRepository.findFirstByName(deadlineType.getName());
        if (ddlType != null)
            deadlineType.setId(ddlType.getId());

        return deadlineTypeRepository.save(deadlineType);
    }

    /**
     * Get all the DeadlineTypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    public Page<DeadlineType> findAll(Pageable pageable) {
        log.debug("Request to get all DeadlineTypes");
        return deadlineTypeRepository.findAll(pageable);
    }


    /**
     * Get one DeadlineType by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<DeadlineType> findOne(String id) {
        log.debug("Request to get DeadlineType : {}", id);
        return deadlineTypeRepository.findById(id);
    }

    /**
     * Delete the DeadlineType by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete DeadlineType : {}", id);
        deadlineTypeRepository.deleteById(id);
    }
}
