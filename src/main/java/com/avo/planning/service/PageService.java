package com.avo.planning.service;

import com.avo.planning.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Page.
 */
public interface PageService {

    /**
     * Save a page.
     *
     * @param page the entity to save
     * @return the persisted entity
     */
    Page save(Page page);

    /**
     * Get all the pages.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    org.springframework.data.domain.Page<Page> findAll(Pageable pageable);


    /**
     * Get the "id" page.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Page> findOne(String id);

    /**
     * Delete the "id" page.
     *
     * @param id the id of the entity
     */
    void delete(String id);
}
