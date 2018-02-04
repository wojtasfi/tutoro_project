package com.relationservice.service;

import com.relationservice.dao.neo4j.LearningRelationshipRepository;
import com.relationservice.dao.neo4j.TeachingRelationshipRepository;
import com.relationservice.dao.neo4j.TutorNodeRepository;
import com.relationservice.entities.db.LearnRelation;
import com.relationservice.entities.neo4j.LearningRelationship;
import com.relationservice.entities.neo4j.TeachingRelationship;
import com.relationservice.entities.neo4j.TutorNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wojciech on 28.06.17.
 */
@Service
public class TutorNodeService {
    private static Logger LOGGER = LoggerFactory.getLogger(TutorNodeService.class);

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
            LOGGER.info("{} does not exists. Creating", learnRelation.getStudentUsername());
            student = new TutorNode();
            student.setName(learnRelation.getStudentName());
            student.setLastName(learnRelation.getStudentLastName());
            student.setEmail(learnRelation.getStudentEmail());
            student.setSkype(learnRelation.getStudentSkype());
            student.setUsername(learnRelation.getStudentUsername());
            tutorNodeRepository.save(student);
        } else {
            LOGGER.info("{} does exists.", learnRelation.getStudentUsername());

            student = tutorNodeRepository.findByUsername(learnRelation.getStudentUsername());
        }

        if (!nodeExists(learnRelation.getTeacherUsername())) {
            LOGGER.info("{} does not exists. Creating", learnRelation.getTeacherUsername());

            teacher = new TutorNode();
            teacher.setName(learnRelation.getTeacherName());
            teacher.setLastName(learnRelation.getTeacherLastName());
            teacher.setEmail(learnRelation.getTeacherEmail());
            teacher.setSkype(learnRelation.getTeacherSkype());
            teacher.setUsername(learnRelation.getTeacherUsername());
            tutorNodeRepository.save(teacher);
        } else {
            LOGGER.info("{} doeas not exists. Creating", learnRelation.getTeacherUsername());

            teacher = tutorNodeRepository.findByUsername(learnRelation.getTeacherUsername());
        }

        TeachingRelationship teachingRelationship = new TeachingRelationship();
        teachingRelationship.setSkill(learnRelation.getSkill());
        teachingRelationship.setSkillId(learnRelation.getSkillId());
        teachingRelationship.setStudent(student);
        teachingRelationship.setTeacher(teacher);
        LOGGER.info("Saving teaching relationship with id {}", teachingRelationship.getId());
        teachingRelationshipRepository.save(teachingRelationship);

        LearningRelationship learningRelationship = new LearningRelationship();
        learningRelationship.setSkill(learnRelation.getSkill());
        learningRelationship.setSkillId(learnRelation.getSkillId());
        learningRelationship.setStudent(student);
        learningRelationship.setTeacher(teacher);
        LOGGER.info("Saving learning relationship with id {}", learningRelationship.getId());

        learningRelationshipRepository.save(learningRelationship);
    }

    private boolean nodeExists(String username) {
        LOGGER.info("Checking if node exists: {}", username);
        TutorNode tutorNode = tutorNodeRepository.findByUsername(username);

        if (tutorNode != null) {
            return true;
        }
        return false;
    }
}
