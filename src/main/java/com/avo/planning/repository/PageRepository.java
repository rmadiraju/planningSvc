package com.avo.planning.repository;

import com.avo.planning.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the Instrument entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PageRepository extends MongoRepository<Page, String> {
    Page findFirstByName(String name);
}
