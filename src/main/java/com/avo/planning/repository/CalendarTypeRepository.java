package com.avo.planning.repository;

import com.avo.planning.domain.CalendarType;
import com.avo.planning.domain.enumeration.CalendarScopeEnum;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the CalendarType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CalendarTypeRepository extends MongoRepository<CalendarType, String> {

    CalendarType findFirstByNameAndScope(String name, CalendarScopeEnum scopeEnum);
}
