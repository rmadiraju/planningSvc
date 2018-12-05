package com.avo.planning.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.avo.planning.domain.CalendarType;
import com.avo.planning.service.CalendarTypeService;
import com.avo.planning.web.rest.errors.BadRequestAlertException;
import com.avo.planning.web.rest.util.HeaderUtil;
import com.avo.planning.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing CalendarType.
 */
@RestController
@RequestMapping("/api")
public class CalendarTypeResource {

    private final Logger log = LoggerFactory.getLogger(CalendarTypeResource.class);

    private static final String ENTITY_NAME = "avotestCalendarType";

    private final CalendarTypeService calendarTypeService;

    public CalendarTypeResource(CalendarTypeService calendarTypeService) {
        this.calendarTypeService = calendarTypeService;
    }

    /**
     * POST  /calendar-types : Create a new calendarType.
     *
     * @param calendarType the calendarType to create
     * @return the ResponseEntity with status 201 (Created) and with body the new calendarType, or with status 400 (Bad Request) if the calendarType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/calendar-types")
    @Timed
    public ResponseEntity<CalendarType> createCalendarType(@Valid @RequestBody CalendarType calendarType) throws URISyntaxException {
        log.debug("REST request to save CalendarType : {}", calendarType);
        if (calendarType.getId() != null) {
            throw new BadRequestAlertException("A new calendarType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CalendarType result = calendarTypeService.save(calendarType);
        return ResponseEntity.created(new URI("/api/calendar-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /calendar-types : Updates an existing calendarType.
     *
     * @param calendarType the calendarType to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated calendarType,
     * or with status 400 (Bad Request) if the calendarType is not valid,
     * or with status 500 (Internal Server Error) if the calendarType couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/calendar-types")
    @Timed
    public ResponseEntity<CalendarType> updateCalendarType(@Valid @RequestBody CalendarType calendarType) throws URISyntaxException {
        log.debug("REST request to update CalendarType : {}", calendarType);
        if (calendarType.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CalendarType result = calendarTypeService.save(calendarType);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, calendarType.getId().toString()))
            .body(result);
    }

    /**
     * GET  /calendar-types : get all the calendarTypes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of calendarTypes in body
     */
    @GetMapping("/calendar-types")
    @Timed
    public ResponseEntity<List<CalendarType>> getAllCalendarTypes(Pageable pageable) {
        log.debug("REST request to get a page of CalendarTypes");
        Page<CalendarType> page = calendarTypeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/calendar-types");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /calendar-types/:id : get the "id" calendarType.
     *
     * @param id the id of the calendarType to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the calendarType, or with status 404 (Not Found)
     */
    @GetMapping("/calendar-types/{id}")
    @Timed
    public ResponseEntity<CalendarType> getCalendarType(@PathVariable String id) {
        log.debug("REST request to get CalendarType : {}", id);
        Optional<CalendarType> calendarType = calendarTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(calendarType);
    }

    /**
     * DELETE  /calendar-types/:id : delete the "id" calendarType.
     *
     * @param id the id of the calendarType to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/calendar-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteCalendarType(@PathVariable String id) {
        log.debug("REST request to delete CalendarType : {}", id);
        calendarTypeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
