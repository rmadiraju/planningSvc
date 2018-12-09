package com.avo.planning.service;

import com.avo.planning.domain.Campaign;
import com.avo.planning.domain.Deadline;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Deadlines.
 */
public interface DeadlineService {

    /**
     * Save a Deadline.
     *
     * @param Deadline the entity to save
     * @return the persisted entity
     */
    Deadline save(Deadline deadline);

    /**
     * Get all the Deadline.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Deadline> findAll(Pageable pageable);


    /**
     * Get the "id" Deadline.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Deadline> findOne(String id);

    /**
     * Delete the "id" Deadline.
     *
     * @param id the id of the entity
     */
    void delete(String id);

    Optional<Deadline> findByName(String name);



}
