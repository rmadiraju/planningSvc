package com.avo.planning.service;

import com.avo.planning.domain.Instrument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Instrument.
 */
public interface InstrumentService {

    /**
     * Save a instrument.
     *
     * @param instrument the entity to save
     * @return the persisted entity
     */
    Instrument save(Instrument instrument);

    /**
     * Get all the instruments.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Instrument> findAll(Pageable pageable);


    /**
     * Get the "id" instrument.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Instrument> findOne(String id);

    /**
     * Delete the "id" instrument.
     *
     * @param id the id of the entity
     */
    void delete(String id);
}
