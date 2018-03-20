package com.recommendation.dao;

import com.recommendation.results.RecommendedTeacherResult;
import org.neo4j.ogm.model.Node;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRecommendationRepository extends GraphRepository<Node> {

    @Query("MATCH (t:TutorNode) WHERE t.username={0} WITH t as tutor MATCH (tutor)-[:IS_LEARNING]->(skill:SkillNode)" +
            "<-[:IS_TEACHING]-(recommendedTeacher:TutorNode) " +
            "WHERE NOT (recommendedTeacher)-[:TEACHING]->(tutor) RETURN recommendedTeacher.username " +
            "as teacherUsername, skill.name as skillName")
    List<RecommendedTeacherResult> getRecommendedTeachers(String tutorUsername);
}
