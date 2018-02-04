package com.relationservice.service;

import com.relationservice.dao.neo4j.LearningRelationshipRepository;
import com.relationservice.dao.neo4j.TeachingRelationshipRepository;
import com.relationservice.dao.neo4j.TutorNodeRepository;
import com.relationservice.entities.db.LearnRelation;
import com.relationservice.entities.neo4j.LearningRelationship;
import com.relationservice.entities.neo4j.TeachingRelationship;
import com.relationservice.entities.neo4j.TutorNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wojciech on 28.06.17.
 */
@Service
public class TutorNodeService {

    @Autowired
    private TutorNodeRepository tutorNodeRepository;

    @Autowired
    private LearningRelationshipRepository learningRelationshipRepository;

    @Autowired
    private TeachingRelationshipRepository teachingRelationshipRepository;

    public void saveLearnRelation(LearnRelation learnRelation) {

        TutorNode student;
        TutorNode teacher;

        if (!nodeExists(learnRelation.getStudentUsername())) {
            student = new TutorNode();
            student.setName(learnRelation.getStudentName());
            student.setLastName(learnRelation.getStudentLastName());
            student.setEmail(learnRelation.getStudentEmail());
            student.setSkype(learnRelation.getStudentSkype());
            student.setUsername(learnRelation.getStudentUsername());
            tutorNodeRepository.save(student);
        } else {
            student = tutorNodeRepository.findByUsername(learnRelation.getStudentUsername());
        }

        if (!nodeExists(learnRelation.getTeacherUsername())) {
            teacher = new TutorNode();
            teacher.setName(learnRelation.getTeacherName());
            teacher.setLastName(learnRelation.getTeacherLastName());
            teacher.setEmail(learnRelation.getTeacherEmail());
            teacher.setSkype(learnRelation.getTeacherSkype());
            teacher.setUsername(learnRelation.getTeacherUsername());
            tutorNodeRepository.save(teacher);
        } else {
            teacher = tutorNodeRepository.findByUsername(learnRelation.getTeacherUsername());
        }

        TeachingRelationship teachingRelationship = new TeachingRelationship();
        teachingRelationship.setSkill(learnRelation.getSkill());
        teachingRelationship.setSkillId(learnRelation.getSkillId());
        teachingRelationship.setStudent(student);
        teachingRelationship.setTeacher(teacher);
        teachingRelationshipRepository.save(teachingRelationship);

        LearningRelationship learningRelationship = new LearningRelationship();
        learningRelationship.setSkill(learnRelation.getSkill());
        learningRelationship.setSkillId(learnRelation.getSkillId());
        learningRelationship.setStudent(student);
        learningRelationship.setTeacher(teacher);
        learningRelationshipRepository.save(learningRelationship);
    }

    private boolean nodeExists(String username) {
        TutorNode tutorNode = tutorNodeRepository.findByUsername(username);

        if (tutorNode != null) {
            return true;
        }
        return false;
    }
}
