package com.avo.planning.repository;

import com.avo.planning.domain.CampaignType;
import com.avo.planning.domain.enumeration.CampaignTypeEnum;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the CampaignType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CampaignTypeRepository extends MongoRepository<CampaignType, String> {
    CampaignType findFirstByNameAndType(String name, CampaignTypeEnum campaignTypeEnum);
}
