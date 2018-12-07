package com.avo.planning.service.impl;

import com.avo.planning.domain.Calendar;
import com.avo.planning.domain.CalendarType;
import com.avo.planning.repository.AttributeRepository;
import com.avo.planning.repository.CalendarRepository;
import com.avo.planning.service.CalendarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Calendar.
 */
@Service
public class CalendarServiceImpl extends AbstractServiceImpl implements CalendarService {

    private final Logger log = LoggerFactory.getLogger(CalendarServiceImpl.class);

    private final CalendarRepository calendarRepository;


    public CalendarServiceImpl(CalendarRepository calendarRepository) {
        this.calendarRepository = calendarRepository;
    }

    /**
     * Save a calendar.
     *
     * @param calendar the entity to save
     * @return the persisted entity
     */
    @Override
    public Calendar save(Calendar calendar) {
        log.debug("Request to save Calendar : {}", calendar);
        calendar.getAttributes().forEach(a -> a.setId(attributeService.save(a).getId()));

        if (calendar.getSourceTemplate() != null)
            calendar.getSourceTemplate().setId(templateService.save(calendar.getSourceTemplate()).getId());

        if (calendar.getCalendarType() != null)
            calendar.getCalendarType().setId(calendarTypeService.save(calendar.getCalendarType()).getId());

        Optional <Calendar> cal = calendarService.findOneByName(calendar.getName());
        if (cal.isPresent())
            calendar.setId(cal.get().getId());

        //calendar.getCampaigns().forEach(c -> campaignService.save(c));
        return calendarRepository.save(calendar);
    }

    /**
     * Get all the calendars.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    public Page<Calendar> findAll(Pageable pageable) {
        log.debug("Request to get all Calendars");
        //pageable = new PageRequest(0, Integer.MAX_VALUE);
        return calendarRepository.findAll(pageable);
    }

    /**
     * Get all the calendars.
     *
     * @param calendarType the Calendar type
     * @return the list of entities
     */
    @Override
    public List<Calendar> findByCalendarType(CalendarType calendarType) {
        log.debug("Request to get all Calendars by Type");
        return calendarRepository.findByCalendarType(calendarType);
    }

    /**
     * Get one calendar by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<Calendar> findOne(String id) {
        log.debug("Request to get Calendar : {}", id);
        return calendarRepository.findById(id);
    }

    /**
     * Get one calendar by name.
     *
     * @param name the id of the entity
     * @return the entity
     */
    @Override
    public Optional<Calendar> findOneByName(String name) {
        log.debug("Request to get Calendar : {}", name);
        return calendarRepository.findFirstByName(name);
    }


    /**
     * Delete the calendar by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Calendar : {}", id);
        calendarRepository.deleteById(id);
    }

    @Override
    public Optional<Calendar> getCalendarWithCascadingObjects(String name) {
        Optional<Calendar> calendar = calendarService.findOneByName(name);
        if (calendar.isPresent()){
            calendar.get().getCampaigns().addAll(campaignService.findCampaignsByCalendar(name));
            return calendar;
        }else {
            return Optional.empty();
        }
    }

    @Override
    public List<Calendar> getCalendarWithCascadingObjects() {

        List<Calendar> calendars = calendarRepository.findAll();
        calendars.forEach(calendar -> calendar.getCampaigns().addAll(campaignService.findCampaignsByCalendar(calendar.getName())));
        return calendars;

    }



}
