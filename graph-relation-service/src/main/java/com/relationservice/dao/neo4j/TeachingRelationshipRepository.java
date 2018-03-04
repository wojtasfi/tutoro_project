package com.relationservice.dao.neo4j;

import com.relationservice.entities.neo4j.TeachingRelationship;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wojciech on 28.06.17.
 */
@Repository
public interface TeachingRelationshipRepository extends GraphRepository<TeachingRelationship> {
    List<TeachingRelationship> findBySkill(String skill);
}
