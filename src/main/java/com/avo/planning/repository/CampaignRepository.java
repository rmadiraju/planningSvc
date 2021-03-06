package com.avo.planning.repository;

import com.avo.planning.domain.Campaign;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;


/**
 * Spring Data MongoDB repository for the Campaign entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CampaignRepository extends MongoRepository<Campaign, String> {
    Campaign findFirstByName(String name);
    Optional<List<Campaign>> findCampaignsByCalendar(String name);
    Optional<Campaign> findByName(String name);
    List<Campaign> findAll();
    @Query("SELECT e FROM Campaign e WHERE e.startDate >= :startDate AND e.endDate <= :endDate")
    Optional<Campaign> findByStartDateAndEndDate(Date startDate, Date endDate);
}
