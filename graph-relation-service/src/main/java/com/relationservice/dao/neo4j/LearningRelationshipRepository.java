package com.relationservice.dao.neo4j;

import com.relationservice.entities.neo4j.LearningFromRelationship;
import com.relationservice.entities.neo4j.TutorNode;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wojciech on 28.06.17.
 */
@Repository
public interface LearningRelationshipRepository extends GraphRepository<LearningFromRelationship> {
    List<LearningFromRelationship> findBySkill(String skill);

    List<LearningFromRelationship> findByTeacher(TutorNode teacher);

    List<LearningFromRelationship> findByStudent(TutorNode student);

    List<LearningFromRelationship> findByStudentAndTeacher(TutorNode student, TutorNode teacher);

    List<LearningFromRelationship> findByStudentAndTeacherAndSkill(TutorNode student, TutorNode teacher, String skill);
}
