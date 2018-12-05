package com.avo.planning.repository;

import com.avo.planning.domain.Attribute;
import com.avo.planning.domain.enumeration.AttributeEntityType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the Attribute entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AttributeRepository extends MongoRepository<Attribute, String> {
    Attribute findAttributeByName(String name);
    Attribute findAttributeByNameAndEntityType(String name, AttributeEntityType attributeEntityType);
    Attribute findFirstByNameAndEntityType(String name, AttributeEntityType attributeEntityType);


}
