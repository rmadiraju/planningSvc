package com.avo.planning.service;

import com.avo.planning.domain.CalendarType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing CalendarType.
 */
public interface CalendarTypeService {

    /**
     * Save a calendarType.
     *
     * @param calendarType the entity to save
     * @return the persisted entity
     */
    CalendarType save(CalendarType calendarType);

    /**
     * Get all the calendarTypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CalendarType> findAll(Pageable pageable);


    /**
     * Get the "id" calendarType.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CalendarType> findOne(String id);

    /**
     * Delete the "id" calendarType.
     *
     * @param id the id of the entity
     */
    void delete(String id);
}
