package com.avo.planning.repository;

import com.avo.planning.domain.Calendar;
import com.avo.planning.domain.CalendarType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * Spring Data MongoDB repository for the Calendar entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CalendarRepository extends MongoRepository<Calendar, String> {
    List<Calendar> findByCalendarType(CalendarType type);
    Optional<Calendar> findFirstByName(String name);
    Optional<Calendar> findByActiveTrue();
    Optional<Calendar> findByActiveFalse();

}
