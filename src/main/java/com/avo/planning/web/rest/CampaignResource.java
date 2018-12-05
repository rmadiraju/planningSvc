package com.avo.planning.web.rest;

import com.avo.planning.domain.Calendar;
import com.codahale.metrics.annotation.Timed;
import com.avo.planning.domain.Campaign;
import com.avo.planning.service.CampaignService;
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
 * REST controller for managing Campaign.
 */
@RestController
@RequestMapping("/api")
public class CampaignResource {

    private final Logger log = LoggerFactory.getLogger(CampaignResource.class);

    private static final String ENTITY_NAME = "avotestCampaign";

    private final CampaignService campaignService;

    public CampaignResource(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    /**
     * POST  /campaigns : Create a new campaign.
     *
     * @param campaign the campaign to create
     * @return the ResponseEntity with status 201 (Created) and with body the new campaign, or with status 400 (Bad Request) if the campaign has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/campaigns")
    @Timed
    public ResponseEntity<Campaign> createCampaign(@Valid @RequestBody Campaign campaign) throws URISyntaxException {
        log.debug("REST request to save Campaign : {}", campaign);
        if (campaign.getId() != null) {
            throw new BadRequestAlertException("A new campaign cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Campaign result = campaignService.save(campaign);
        return ResponseEntity.created(new URI("/api/campaigns/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /campaigns : Updates an existing campaign.
     *
     * @param campaign the campaign to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated campaign,
     * or with status 400 (Bad Request) if the campaign is not valid,
     * or with status 500 (Internal Server Error) if the campaign couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/campaigns")
    @Timed
    public ResponseEntity<Campaign> updateCampaign(@Valid @RequestBody Campaign campaign) throws URISyntaxException {
        log.debug("REST request to update Campaign : {}", campaign);
//        if (campaign.getId() == null) {
//            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
//        }
        Campaign result = campaignService.save(campaign);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, campaign.getId().toString()))
            .body(result);
    }

    /**
     * GET  /campaigns : get all the campaigns.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of campaigns in body
     */
    @GetMapping("/campaigns")
    @Timed
    public ResponseEntity<List<Campaign>> getAllCampaigns(Pageable pageable) {
        log.debug("REST request to get a page of Campaigns");
        Page<Campaign> page = campaignService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/campaigns");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /campaigns/:id : get the "id" campaign.
     *
     * @param id the id of the campaign to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the campaign, or with status 404 (Not Found)
     */
    @GetMapping("/campaigns/{id}")
    @Timed
    public ResponseEntity<Campaign> getCampaign(@PathVariable String id) {
        log.debug("REST request to get Campaign : {}", id);
        Optional<Campaign> campaign = campaignService.findOne(id);
        return ResponseUtil.wrapOrNotFound(campaign);
    }

    /**
     * DELETE  /campaigns/:id : delete the "id" campaign.
     *
     * @param id the id of the campaign to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/campaigns/{id}")
    @Timed
    public ResponseEntity<Void> deleteCampaign(@PathVariable String id) {
        log.debug("REST request to delete Campaign : {}", id);
        campaignService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }

    @PostMapping("/campaigns/{calendar}")
    @Timed
    public ResponseEntity<List<Campaign>> getCampaign(@RequestBody Calendar calendar) {
        log.debug("REST request to get Campaign by Calendar: {}", calendar);
        List<Campaign> campaigns = campaignService.findCampaignsByCalendar(calendar.getName());
        return ResponseEntity.ok().body(campaigns);
    }

    @PostMapping("/campaigns/list")
    @Timed
    public String createCampaignList(@Valid @RequestBody List<Campaign> campaignList) throws URISyntaxException {
        log.debug("REST request to save Calendar : {}", campaignList);

        campaignList.forEach(campaign -> campaignService.save(campaign));

        return "Done";
    }
}
