package com.avo.planning.web.rest;

import com.avo.planning.domain.InstrumentType;
import com.avo.planning.service.CampaignService;
import com.avo.planning.service.InstrumentTypeService;
import com.codahale.metrics.annotation.Timed;
import com.avo.planning.domain.Instrument;
import com.avo.planning.service.InstrumentService;
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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing Instrument.
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class InstrumentResource {

    private final Logger log = LoggerFactory.getLogger(InstrumentResource.class);

    private static final String ENTITY_NAME = "avotestInstrument";

    private final InstrumentService instrumentService;

    @Autowired
    private InstrumentTypeService instrumentTypeService;

    public InstrumentResource(InstrumentService instrumentService) {
        this.instrumentService = instrumentService;
    }

    /**
     * POST  /instruments : Create a new instrument.
     *
     * @param instrument the instrument to create
     * @return the ResponseEntity with status 201 (Created) and with body the new instrument, or with status 400 (Bad Request) if the instrument has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/instruments")
    @Timed
    public ResponseEntity<Instrument> createInstrument(@Valid @RequestBody Instrument instrument) throws URISyntaxException {
        log.debug("REST request to save Instrument : {}", instrument);
        if (instrument.getId() != null) {
            throw new BadRequestAlertException("A new instrument cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Instrument result = instrumentService.save(instrument);
        return ResponseEntity.created(new URI("/api/instruments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /instruments : Updates an existing instrument.
     *
     * @param instrument the instrument to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated instrument,
     * or with status 400 (Bad Request) if the instrument is not valid,
     * or with status 500 (Internal Server Error) if the instrument couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/instruments")
    @Timed
    public ResponseEntity<Instrument> updateInstrument(@Valid @RequestBody Instrument instrument) throws URISyntaxException {
        log.debug("REST request to update Instrument : {}", instrument);
        if (instrument.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Instrument result = instrumentService.save(instrument);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, instrument.getId().toString()))
            .body(result);
    }

    /**
     * GET  /instruments : get all the instruments.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of instruments in body
     */
    @GetMapping("/instruments")
    @Timed
    public ResponseEntity<List<Instrument>> getAllInstruments(Pageable pageable) {
        log.debug("REST request to get a page of Instruments");
        Page<Instrument> page = instrumentService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/instruments");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /instruments/:id : get the "id" instrument.
     *
     * @param id the id of the instrument to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the instrument, or with status 404 (Not Found)
     */
    @GetMapping("/instruments/{id}")
    @Timed
    public ResponseEntity<Instrument> getInstrument(@PathVariable String id) {
        log.debug("REST request to get Instrument : {}", id);
        Optional<Instrument> instrument = instrumentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(instrument);
    }

    /**
     * DELETE  /instruments/:id : delete the "id" instrument.
     *
     * @param id the id of the instrument to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/instruments/{id}")
    @Timed
    public ResponseEntity<Void> deleteInstrument(@PathVariable String id) {
        log.debug("REST request to delete Instrument : {}", id);
        instrumentService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }

    @GetMapping("/getInstrumentsTypes")
    @Timed
    public ResponseEntity<List<String>> getInstrumentTypes(Pageable pageable) {
        log.debug("REST request to get Instrument Types ");
        Page<InstrumentType> page = instrumentTypeService.findAll(pageable);
        List<String> instList =  page.getContent().stream().map(instrumentType -> instrumentType.getName()).collect(Collectors.toList());
        return ResponseEntity.ok().body(instList);

    }


}
