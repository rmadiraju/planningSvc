package com.avo.planning.web.rest;

import com.avo.planning.domain.CalendarType;
import com.avo.planning.service.CalendarTypeService;
import com.avo.planning.service.CampaignService;
import com.codahale.metrics.annotation.Timed;
import com.avo.planning.domain.Calendar;
import com.avo.planning.service.CalendarService;
import com.avo.planning.web.rest.errors.BadRequestAlertException;
import com.avo.planning.web.rest.util.HeaderUtil;
import com.avo.planning.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Calendar.
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class CalendarResource {

    private final Logger log = LoggerFactory.getLogger(CalendarResource.class);

    private static final String ENTITY_NAME = "avotestCalendar";

    private final CalendarService calendarService;

    @Autowired
    private CalendarTypeService calendarTypeService;


    public CalendarResource(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    /**
     * POST  /calendars : Create a new calendar.
     *
     * @param calendar the calendar to create
     * @return the ResponseEntity with status 201 (Created) and with body the new calendar, or with status 400 (Bad Request) if the calendar has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/calendars")
    @Timed
    public ResponseEntity<Calendar> createCalendar(@Valid @RequestBody Calendar calendar) throws URISyntaxException {
        log.debug("REST request to save Calendar : {}", calendar);
        if (calendar.getId() != null) {
            throw new BadRequestAlertException("A new calendar cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Calendar result = calendarService.save(calendar);
        return ResponseEntity.created(new URI("/api/calendars/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PostMapping("/calendars/list")
    @Timed
    public String createCalendarList(@Valid @RequestBody List<Calendar> calendarList) throws URISyntaxException {
        log.debug("REST request to save Calendar : {}", calendarList);

        calendarList.forEach(calendar -> calendarService.save(calendar));
        //Calendar result = calendarService.save(calendar);
        //return ResponseEntity.created(new URI("/api/calendars/" + result.getId()))
        //    .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
        //    .body(result);
        return "Done";
    }
    /**
     * PUT  /calendars : Updates an existing calendar.
     *
     * @param calendar the calendar to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated calendar,
     * or with status 400 (Bad Request) if the calendar is not valid,
     * or with status 500 (Internal Server Error) if the calendar couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/calendars")
    @Timed
    public ResponseEntity<Calendar> updateCalendar(@Valid @RequestBody Calendar calendar) throws URISyntaxException {
        log.debug("REST request to update Calendar : {}", calendar);

        Calendar result = calendarService.save(calendar);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, calendar.getId().toString()))
            .body(result);
    }


    /**
     * GET  /calendars : get all the calendars.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of calendars in body
     */
    @GetMapping("/calendars")
    @Timed
    public ResponseEntity<List<Calendar>> getAllCalendars(Pageable pageable) {
        log.debug("REST request to get a page of Calendars");
        Page<Calendar> page = calendarService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/calendars");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET /activeCalendars/date
     * @param pageable
     * @param date
     * @return
     */
    @GetMapping("/activeCalendars/{date}")
    @Timed
    public ResponseEntity<List<Calendar>> getActiveCalendars(Pageable pageable, @PathVariable(value = "date", required = false) String date) {
        log.debug("REST request to get a page of Calendars for given date");
        Page<Calendar> page = calendarService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/calendars");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }


    /**
     * GET  /calendars/:id : get the "id" calendar.
     *
     * @param id the id of the calendar to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the calendar, or with status 404 (Not Found)
     */
    @GetMapping("/calendars/{id}")
    @Timed
    public ResponseEntity<Calendar> getCalendar(@PathVariable String id) {
        log.debug("REST request to get Calendar : {}", id);
        Optional<Calendar> calendar = calendarService.findOne(id);
        return ResponseUtil.wrapOrNotFound(calendar);
    }

    /**
     * DELETE  /calendars/:id : delete the "id" calendar.
     *
     * @param id the id of the calendar to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/calendars/{id}")
    @Timed
    public ResponseEntity<Void> deleteCalendar(@PathVariable String id) {
        log.debug("REST request to delete Calendar : {}", id);
        calendarService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }

    /**
     * GET  /calendars : get all the calendars.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of calendars in body
     */
    @PostMapping("/calendars/{type}")
    @Timed
    public ResponseEntity<List<Calendar>> getCalendarsByType(@RequestBody CalendarType type) {
        log.debug("REST request to get all Calendars by Type {} ", type);
        List<Calendar> calendars = calendarService.findByCalendarType(type);

        return ResponseEntity.ok().body(calendars);
    }

    @GetMapping("/calendars/{calType}")
    @Timed
    public ResponseEntity<List<Calendar>> getCalendarsByType(@PathVariable String calType) {
        log.debug("REST request to get all Calendars by Type {} ", calType);
        List<Calendar> calendars = new ArrayList<>();
        Optional<CalendarType> calTypeValue = calendarTypeService.findOne(calType);
        if (calTypeValue.isPresent()) {
            calendars = calendarService.findByCalendarType(calTypeValue.get());
        }else{
            log.info("No Calendar Entities found for Calendar Type {} ", calType);
        }

        return ResponseEntity.ok().body(calendars);
    }

    @GetMapping("/calendars/{name}/{cascade}")
    @Timed
    public ResponseEntity<Optional<Calendar>> getCalendarCascade(@PathVariable String name, @PathVariable boolean cascade) {
        log.debug("REST request to get Calendars for Name {} with Cascading option {} ", name, cascade);

        if (cascade)
            return ResponseEntity.ok().body(calendarService.getCalendarWithCascadingObjects(name));
        else
            return ResponseEntity.ok().body(calendarService.findOneByName(name));
    }

    @GetMapping("/calendarsAll")
    @Timed
    public ResponseEntity<List<Calendar>> getCalendarCascade( ) {

            return ResponseEntity.ok().body(calendarService.getCalendarWithCascadingObjects());
    }
}
