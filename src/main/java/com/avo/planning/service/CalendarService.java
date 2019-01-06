package com.avo.planning.service;

import com.avo.planning.domain.Calendar;
import com.avo.planning.domain.CalendarType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Calendar.
 */
public interface CalendarService {

    /**
     * Save a calendar.
     *
     * @param calendar the entity to save
     * @return the persisted entity
     */
    Calendar save(Calendar calendar);

    /**
     * Get all the calendars.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Calendar> findAll(Pageable pageable);


    /**
     * Get all the calendars.
     *
     * @param calendarType the CalendarType
     * @return the list of entities
     */
    List<Calendar> findByCalendarType(CalendarType calendarType);

    /**
     * Get the "id" calendar.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Calendar> findOne(String id);

    /**
     * Delete the "id" calendar.
     *
     * @param id the id of the entity
     */
    void delete(String id);

    Optional<Calendar> findOneByName(String name) ;

    Optional<Calendar> getCalendarWithCascadingObjects(String name) ;

    List<Calendar> getCalendarWithCascadingObjects() ;


    List<Calendar> findActiveForPeriod(Date startDate, Date endDate);
}
