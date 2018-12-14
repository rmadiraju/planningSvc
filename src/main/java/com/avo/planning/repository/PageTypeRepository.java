package com.avo.planning.repository;

import com.avo.planning.domain.InstrumentType;
import com.avo.planning.domain.PageType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the InstrumentType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PageTypeRepository extends MongoRepository<PageType, String> {
}
