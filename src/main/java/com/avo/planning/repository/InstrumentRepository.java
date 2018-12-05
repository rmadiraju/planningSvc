package com.avo.planning.repository;

import com.avo.planning.domain.Instrument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the Instrument entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InstrumentRepository extends MongoRepository<Instrument, String> {
    Instrument findFirstByName(String name);
}
