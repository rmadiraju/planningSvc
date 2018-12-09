package com.avo.planning.repository;



import com.avo.planning.domain.DeadlineType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the CalendarType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DeadlineTypeRepository extends MongoRepository<DeadlineType, String> {

    DeadlineType findFirstByName(String name);

}
