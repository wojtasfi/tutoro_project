package com.relationservice.dao.neo4j;

import com.relationservice.entities.neo4j.LearningFromRelationship;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by wojciech on 28.06.17.
 */
@Repository
public interface LearningRelationshipRepository extends GraphRepository<LearningFromRelationship> {
}
