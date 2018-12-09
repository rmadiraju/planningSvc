package com.avo.planning.repository;

import com.avo.planning.domain.Campaign;
import com.avo.planning.domain.Deadline;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * Spring Data MongoDB repository for the Campaign entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DeadlineRepository extends MongoRepository<Deadline, String> {
    Deadline findFirstByName(String name);

    Optional<Deadline> findByName(String name);

}
