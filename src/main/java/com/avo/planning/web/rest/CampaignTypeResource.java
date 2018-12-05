package com.avo.planning.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.avo.planning.domain.CampaignType;
import com.avo.planning.service.CampaignTypeService;
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
 * REST controller for managing CampaignType.
 */
@RestController
@RequestMapping("/api")
public class CampaignTypeResource {

    private final Logger log = LoggerFactory.getLogger(CampaignTypeResource.class);

    private static final String ENTITY_NAME = "avotestCampaignType";

    private final CampaignTypeService campaignTypeService;

    public CampaignTypeResource(CampaignTypeService campaignTypeService) {
        this.campaignTypeService = campaignTypeService;
    }

    /**
     * POST  /campaign-types : Create a new campaignType.
     *
     * @param campaignType the campaignType to create
     * @return the ResponseEntity with status 201 (Created) and with body the new campaignType, or with status 400 (Bad Request) if the campaignType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/campaign-types")
    @Timed
    public ResponseEntity<CampaignType> createCampaignType(@Valid @RequestBody CampaignType campaignType) throws URISyntaxException {
        log.debug("REST request to save CampaignType : {}", campaignType);
        if (campaignType.getId() != null) {
            throw new BadRequestAlertException("A new campaignType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CampaignType result = campaignTypeService.save(campaignType);
        return ResponseEntity.created(new URI("/api/campaign-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /campaign-types : Updates an existing campaignType.
     *
     * @param campaignType the campaignType to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated campaignType,
     * or with status 400 (Bad Request) if the campaignType is not valid,
     * or with status 500 (Internal Server Error) if the campaignType couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/campaign-types")
    @Timed
    public ResponseEntity<CampaignType> updateCampaignType(@Valid @RequestBody CampaignType campaignType) throws URISyntaxException {
        log.debug("REST request to update CampaignType : {}", campaignType);
        if (campaignType.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CampaignType result = campaignTypeService.save(campaignType);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, campaignType.getId().toString()))
            .body(result);
    }

    /**
     * GET  /campaign-types : get all the campaignTypes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of campaignTypes in body
     */
    @GetMapping("/campaign-types")
    @Timed
    public ResponseEntity<List<CampaignType>> getAllCampaignTypes(Pageable pageable) {
        log.debug("REST request to get a page of CampaignTypes");
        Page<CampaignType> page = campaignTypeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/campaign-types");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /campaign-types/:id : get the "id" campaignType.
     *
     * @param id the id of the campaignType to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the campaignType, or with status 404 (Not Found)
     */
    @GetMapping("/campaign-types/{id}")
    @Timed
    public ResponseEntity<CampaignType> getCampaignType(@PathVariable String id) {
        log.debug("REST request to get CampaignType : {}", id);
        Optional<CampaignType> campaignType = campaignTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(campaignType);
    }

    /**
     * DELETE  /campaign-types/:id : delete the "id" campaignType.
     *
     * @param id the id of the campaignType to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/campaign-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteCampaignType(@PathVariable String id) {
        log.debug("REST request to delete CampaignType : {}", id);
        campaignTypeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
