package com.relationservice.dao.neo4j;

import com.relationservice.entities.neo4j.SkillNode;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillNodeRepository extends GraphRepository<SkillNode> {
    SkillNode findByName(String skillName);
}
