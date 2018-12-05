package com.avo.planning.repository;

import com.avo.planning.domain.Template;
import com.avo.planning.domain.enumeration.TemplateTypeEnum;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the Template entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TemplateRepository extends MongoRepository<Template, String> {
    Template findFirstByNameAndType(String name, TemplateTypeEnum typeEnum);
}
