package com.avo.planning.service.impl;

import com.avo.planning.domain.PageType;
import com.avo.planning.repository.PageTypeRepository;
import com.avo.planning.service.PageTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Interface for managing PageType.
 */
@Service
public class PageTypeServiceImpl implements PageTypeService {


    private final Logger log = LoggerFactory.getLogger(PageTypeServiceImpl.class);

    private final PageTypeRepository pageTypeRepository;


    public PageTypeServiceImpl(PageTypeRepository pageTypeRepository) {
        this.pageTypeRepository = pageTypeRepository;
    }

    @Override
    public PageType save(PageType pageType) {
        return pageTypeRepository.save(pageType);
    }

    @Override
    public Page<PageType> findAll(Pageable pageable) {
        return pageTypeRepository.findAll(pageable);
    }

    @Override
    public Optional<PageType> findOne(String id) {
        return pageTypeRepository.findById(id);
    }

    @Override
    public void delete(String id) {
         pageTypeRepository.deleteById(id);

    }


}
