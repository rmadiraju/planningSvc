package com.avo.planning.web.rest;

import com.avo.planning.domain.Deadline;
import com.avo.planning.domain.DeadlineType;

import com.avo.planning.service.DeadlineService;
import com.avo.planning.service.DeadlineTypeService;

import com.avo.planning.web.rest.errors.BadRequestAlertException;
import com.avo.planning.web.rest.util.HeaderUtil;
import com.avo.planning.web.rest.util.PaginationUtil;
import com.codahale.metrics.annotation.Timed;
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
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing Instrument.
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class DeadlineResource {

    private final Logger log = LoggerFactory.getLogger(DeadlineResource.class);

    private final DeadlineService deadlineService;

    private static final String ENTITY_NAME = "avoDeadline";

    @Autowired
    private DeadlineTypeService deadlineTypeService;

    public DeadlineResource(DeadlineService deadlineService) {
        this.deadlineService = deadlineService;
    }

    /**
     * POST  /instruments : Create a new Deadline.
     *
     * @param Deadline the Deadline to create
     * @return the ResponseEntity with status 201 (Created) and with body the new Deadline, or with status 400 (Bad Request) if the Deadline has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/deadlines")
    @Timed
    public ResponseEntity<Deadline> createDeadline(@Valid @RequestBody Deadline deadline) throws URISyntaxException {
        log.debug("REST request to save Deadline : {}", deadline);
        if (deadline.getId() != null) {
            throw new BadRequestAlertException("A new deadline cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Deadline result = deadlineService.save(deadline);
        return ResponseEntity.created(new URI("/api/deadlines/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /instruments : Updates an existing deadline.
     *
     * @param deadline the deadline to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated deadline,
     * or with status 400 (Bad Request) if the instrument is not valid,
     * or with status 500 (Internal Server Error) if the deadline couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/deadlines")
    @Timed
    public ResponseEntity<Deadline> updateDeadline(@Valid @RequestBody Deadline deadline) throws URISyntaxException {
        log.debug("REST request to update Deadline : {}", deadline);
        if (deadline.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Deadline result = deadlineService.save(deadline);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, deadline.getId().toString()))
            .body(result);
    }


    @GetMapping("/getDeadlineTypes")
    @Timed
    public ResponseEntity<List<DeadlineType>> getDeadlineTypes(Pageable pageable) {
        log.debug("REST request to get Deadline Types ");
        Page<DeadlineType> page = deadlineTypeService.findAll(pageable);
        //Map<String, String> ddlList =  page.getContent().stream().map(deadlineType -> deadlineType.getName()).collect(Collectors.toList());
        //Map<String, String> ddlList =  page.getContent().stream().collect(Collectors.toMap(DeadlineType::getName, DeadlineType::getDescription));
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/getDeadlineTypes");
        return ResponseEntity.ok().headers(headers).body(page.getContent());

    }


}
