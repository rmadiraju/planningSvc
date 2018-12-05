package com.avo.planning.service.impl;

import com.avo.planning.domain.CalendarType;
import com.avo.planning.repository.CalendarTypeRepository;
import com.avo.planning.service.CalendarTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing CalendarType.
 */
@Service
public class CalendarTypeServiceImpl implements CalendarTypeService {

    private final Logger log = LoggerFactory.getLogger(CalendarTypeServiceImpl.class);

    private final CalendarTypeRepository calendarTypeRepository;

    public CalendarTypeServiceImpl(CalendarTypeRepository calendarTypeRepository) {
        this.calendarTypeRepository = calendarTypeRepository;
    }

    /**
     * Save a calendarType.
     *
     * @param calendarType the entity to save
     * @return the persisted entity
     */
    @Override
    public CalendarType save(CalendarType calendarType) {
        log.debug("Request to save CalendarType : {}", calendarType);
        CalendarType calType = calendarTypeRepository.findFirstByNameAndScope(calendarType.getName(), calendarType.getScope());
        if (calType != null)
            calendarType.setId(calType.getId());
        return calendarTypeRepository.save(calendarType);
    }

    /**
     * Get all the calendarTypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    public Page<CalendarType> findAll(Pageable pageable) {
        log.debug("Request to get all CalendarTypes");
        return calendarTypeRepository.findAll(pageable);
    }


    /**
     * Get one calendarType by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<CalendarType> findOne(String id) {
        log.debug("Request to get CalendarType : {}", id);
        return calendarTypeRepository.findById(id);
    }

    /**
     * Delete the calendarType by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete CalendarType : {}", id);
        calendarTypeRepository.deleteById(id);
    }
}
