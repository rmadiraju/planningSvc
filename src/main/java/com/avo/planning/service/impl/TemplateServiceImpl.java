package com.avo.planning.service.impl;

import com.avo.planning.domain.Template;
import com.avo.planning.repository.AttributeRepository;
import com.avo.planning.repository.TemplateRepository;
import com.avo.planning.service.TemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing Template.
 */
@Service
public class TemplateServiceImpl extends AbstractServiceImpl implements TemplateService {

    private final Logger log = LoggerFactory.getLogger(TemplateServiceImpl.class);

    private final TemplateRepository templateRepository;


    public TemplateServiceImpl(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    /**
     * Save a template.
     *
     * @param template the entity to save
     * @return the persisted entity
     */
    @Override
    public Template save(Template template) {
        log.debug("Request to save Template : {}", template);
        template.getAttributes().forEach(a -> a.setId(attributeService.save(a).getId()));
        Template templ = templateRepository.findFirstByNameAndType(template.getName(), template.getType());
        if (templ != null)
            template.setId(templ.getId());
        return templateRepository.save(template);
    }

    /**
     * Get all the templates.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    public Page<Template> findAll(Pageable pageable) {
        log.debug("Request to get all Templates");
        return templateRepository.findAll(pageable);
    }


    /**
     * Get one template by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<Template> findOne(String id) {
        log.debug("Request to get Template : {}", id);
        return templateRepository.findById(id);
    }

    /**
     * Delete the template by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Template : {}", id);
        templateRepository.deleteById(id);
    }
}
