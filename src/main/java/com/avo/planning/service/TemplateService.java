package com.avo.planning.service;

import com.avo.planning.domain.Template;
import com.avo.planning.domain.enumeration.TemplateTypeEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Template.
 */
public interface TemplateService {

    /**
     * Save a template.
     *
     * @param template the entity to save
     * @return the persisted entity
     */
    Template save(Template template);

    /**
     * Get all the templates.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Template> findAll(Pageable pageable);


    /**
     * Get the "id" template.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Template> findOne(String id);

    /**
     * Delete the "id" template.
     *
     * @param id the id of the entity
     */
    void delete(String id);

    List<Template> findTemplatesByType(TemplateTypeEnum type);
}
