package com.recommendation.dao;

import org.neo4j.ogm.model.Node;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRecommendationRepository extends GraphRepository<Node> {


}
