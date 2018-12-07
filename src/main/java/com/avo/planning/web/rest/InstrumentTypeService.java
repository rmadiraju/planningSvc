package com.avo.planning.web.rest;

import com.avo.planning.domain.InstrumentType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing InstrumentType.
 */
public interface InstrumentTypeService {

    /**
     * Save a instrumentType.
     *
     * @param instrumentType the entity to save
     * @return the persisted entity
     */
    InstrumentType save(InstrumentType instrumentType);

    /**
     * Get all the instrumentTypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<InstrumentType> findAll(Pageable pageable);


    /**
     * Get the "id" instrumentType.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<InstrumentType> findOne(String id);

    /**
     * Delete the "id" instrumentType.
     *
     * @param id the id of the entity
     */
    void delete(String id);
}
