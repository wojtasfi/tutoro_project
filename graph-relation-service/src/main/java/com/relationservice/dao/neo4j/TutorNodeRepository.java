package com.relationservice.dao.neo4j;

import com.relationservice.entities.neo4j.TutorNode;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by wojciech on 28.06.17.
 */
public interface TutorNodeRepository extends GraphRepository<TutorNode> {

    TutorNode findByUsername(String username);
}
