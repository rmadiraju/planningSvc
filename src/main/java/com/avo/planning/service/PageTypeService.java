package com.avo.planning.service;

import com.avo.planning.domain.PageType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing PageType.
 */
public interface PageTypeService {

    /**
     * Save a pageType.
     *
     * @param pageType the entity to save
     * @return the persisted entity
     */
    PageType save(PageType pageType);

    /**
     * Get all the pageTypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PageType> findAll(Pageable pageable);


    /**
     * Get the "id" pageType.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PageType> findOne(String id);

    /**
     * Delete the "id" pageType.
     *
     * @param id the id of the entity
     */
    void delete(String id);
}
