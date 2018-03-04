package com.recommendation.dao;

import com.recommendation.results.RecommendedSkillResult;
import org.neo4j.ogm.model.Node;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRecommendationRepository extends GraphRepository<Node> {

    @Query("MATCH (t:TutorNode) where t.username={0} WITH t as tutot MATCH (tutor)-[:IS_LEARNING]->(:SkillNode)<-[:IS_LEARNING]-" +
            "(:TutorNode)-[:IS_LEARNING]->(recommendedSkill) " +
            "RETURN recommendedSkill.name as skillName, tutor.username AS tutorUsername")
    List<RecommendedSkillResult> findRecommendedSkills(String tutorUsername);
}
