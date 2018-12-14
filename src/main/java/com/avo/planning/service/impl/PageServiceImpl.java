package com.avo.planning.service.impl;

import com.avo.planning.domain.Page;
import com.avo.planning.repository.PageRepository;
import com.avo.planning.service.PageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing Page.
 */
@Service
public class PageServiceImpl extends AbstractServiceImpl implements PageService {

    private final Logger log = LoggerFactory.getLogger(PageServiceImpl.class);

    private final PageRepository pageRepository;


    public PageServiceImpl(PageRepository pageRepository) {
        this.pageRepository = pageRepository;
    }

    /**
     * Save a page.
     *
     * @param page the entity to save
     * @return the persisted entity
     */
    @Override
    public Page save(Page page) {
        log.debug("Request to save Page : {}", page);

        if (page.getSourceTemplate() != null)
            page.getSourceTemplate().setId(templateService.save(page.getSourceTemplate()).getId());

        Page inst = pageRepository.findFirstByName(page.getName());
        if (inst != null)
            page.setId(inst.getId());
        return pageRepository.save(page);
    }

    /**
     * Get all the pages.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    public org.springframework.data.domain.Page<Page> findAll(Pageable pageable) {
        log.debug("Request to get all Pages");
        return pageRepository.findAll(pageable);
    }


    /**
     * Get one page by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<Page> findOne(String id) {
        log.debug("Request to get Page : {}", id);
        return pageRepository.findById(id);
    }

    /**
     * Delete the page by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Page : {}", id);
        pageRepository.deleteById(id);
    }
}
