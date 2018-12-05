package com.avo.planning.repository;

import com.avo.planning.domain.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data MongoDB repository for the Group entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GroupRepository extends MongoRepository<Group, String> {
    @Query("{}")
    Page<Group> findAllWithEagerRelationships(Pageable pageable);

    @Query("{}")
    List<Group> findAllWithEagerRelationships();

    @Query("{'id': ?0}")
    Optional<Group> findOneWithEagerRelationships(String id);

}
