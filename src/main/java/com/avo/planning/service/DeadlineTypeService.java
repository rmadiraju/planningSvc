package com.avo.planning.service;

import com.avo.planning.domain.CalendarType;
import com.avo.planning.domain.DeadlineType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing CalendarType.
 */
public interface DeadlineTypeService {

    /**
     * Save a DeadlineType.
     *
     * @param DeadlineType the entity to save
     * @return the persisted entity
     */
    DeadlineType save(DeadlineType deadlineType);

    /**
     * Get all the calendarTypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<DeadlineType> findAll(Pageable pageable);


    /**
     * Get the "id" calendarType.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<DeadlineType> findOne(String id);

    /**
     * Delete the "id" calendarType.
     *
     * @param id the id of the entity
     */
    void delete(String id);
}
