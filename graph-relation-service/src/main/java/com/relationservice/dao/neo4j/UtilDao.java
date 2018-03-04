package com.relationservice.dao.neo4j;

import org.neo4j.ogm.model.Node;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilDao extends GraphRepository<Node> {

    @Query("MATCH ()-[r]-() DELETE r")
    void removeAllRelationships();

    @Query("MATCH(n) DELETE n")
    void removeAllNodes();
}
