package com.avo.planning.service;

import com.avo.planning.domain.Attribute;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Attribute.
 */
public interface AttributeService {

    /**
     * Save a attribute.
     *
     * @param attribute the entity to save
     * @return the persisted entity
     */
    Attribute save(Attribute attribute);

    /**
     * Get all the attributes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Attribute> findAll(Pageable pageable);


    /**
     * Get the "id" attribute.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Attribute> findOne(String id);

    /**
     * Delete the "id" attribute.
     *
     * @param id the id of the entity
     */
    void delete(String id);
}
