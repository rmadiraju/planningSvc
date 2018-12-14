package com.avo.planning.web.rest;

import com.avo.planning.domain.Page;
import com.avo.planning.domain.PageType;
import com.avo.planning.service.PageService;
import com.avo.planning.service.PageTypeService;
import com.avo.planning.web.rest.errors.BadRequestAlertException;
import com.avo.planning.web.rest.util.HeaderUtil;
import com.avo.planning.web.rest.util.PaginationUtil;
import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
 * REST controller for managing Page.
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class PageResource {

    private final Logger log = LoggerFactory.getLogger(PageResource.class);

    private static final String ENTITY_NAME = "avotestPage";

    private final PageService pageService;

    @Autowired
    private PageTypeService pageTypeService;

    public PageResource(PageService pageService) {
        this.pageService = pageService;
    }

    /**
     * POST  /pages : Create a new page.
     *
     * @param page the page to create
     * @return the ResponseEntity with status 201 (Created) and with body the new page, or with status 400 (Bad Request) if the page has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/pages")
    @Timed
    public ResponseEntity<Page> createPage(@Valid @RequestBody Page page) throws URISyntaxException {
        log.debug("REST request to save Page : {}", page);
        if (page.getId() != null) {
            throw new BadRequestAlertException("A new page cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Page result = pageService.save(page);
        return ResponseEntity.created(new URI("/api/pages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /pages : Updates an existing page.
     *
     * @param page the page to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated page,
     * or with status 400 (Bad Request) if the page is not valid,
     * or with status 500 (Internal Server Error) if the page couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/pages")
    @Timed
    public ResponseEntity<Page> updatePage(@Valid @RequestBody Page page) throws URISyntaxException {
        log.debug("REST request to update Page : {}", page);
        if (page.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Page result = pageService.save(page);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, page.getId().toString()))
            .body(result);
    }

    /**
     * GET  /pages : get all the pages.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of pages in body
     */
    @GetMapping("/pages")
    @Timed
    public ResponseEntity<List<Page>> getAllPages(Pageable pageable) {
        log.debug("REST request to get a page of Pages");
        org.springframework.data.domain.Page<Page> page = pageService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/pages");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /pages/:id : get the "id" page.
     *
     * @param id the id of the page to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the page, or with status 404 (Not Found)
     */
    @GetMapping("/pages/{id}")
    @Timed
    public ResponseEntity<Page> getPage(@PathVariable String id) {
        log.debug("REST request to get Page : {}", id);
        Optional<Page> page = pageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(page);
    }

    /**
     * DELETE  /pages/:id : delete the "id" page.
     *
     * @param id the id of the page to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/pages/{id}")
    @Timed
    public ResponseEntity<Void> deletePage(@PathVariable String id) {
        log.debug("REST request to delete Page : {}", id);
        pageService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }

    @GetMapping("/getPagesTypes")
    @Timed
    public ResponseEntity<List<String>> getPageTypes(Pageable pageable) {
        log.debug("REST request to get Page Types ");
        org.springframework.data.domain.Page<PageType> page = pageTypeService.findAll(pageable);
        List<String> instList =  page.getContent().stream().map(pageType -> pageType.getName()).collect(Collectors.toList());
        return ResponseEntity.ok().body(instList);

    }

    @GetMapping("/getPagesTypesWithDetail")
    @Timed
    public ResponseEntity<List<PageType>> getPagesTypesWithDetail(Pageable pageable) {
        log.debug("REST request to get Page Types ");
        org.springframework.data.domain.Page<PageType> page = pageTypeService.findAll(pageable);

        return ResponseEntity.ok().body(page.getContent());

    }



}
