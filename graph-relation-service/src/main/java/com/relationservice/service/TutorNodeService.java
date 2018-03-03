package com.relationservice.service;

import com.relationservice.dao.neo4j.*;
import com.relationservice.entities.db.LearnRelationRawData;
import com.relationservice.entities.neo4j.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * Created by wojciech on 28.06.17.
 */
@Service
public class TutorNodeService {
    private static Logger LOGGER = LoggerFactory.getLogger(TutorNodeService.class);

    @Autowired
    private TutorNodeRepository tutorNodeRepository;

    @Autowired
    private SkillNodeRepository skillNodeRepository;

    @Autowired
    private LearningRelationshipRepository learningRelationshipRepository;

    @Autowired
    private TeachingRelationshipRepository teachingRelationshipRepository;

    @Autowired
    private IsTeachingRelationshipRepository isTeachingRelationshipRepository;

    @Autowired
    private IsLearningRelationshipRepository isLearningRelationshipRepository;

    public void processLearnRelationRawData(LearnRelationRawData learnRelationRawData) {

        TutorNode student = initStudentNode(learnRelationRawData);
        TutorNode teacher = initTeacherNode(learnRelationRawData);

        createTeachingRelationship(learnRelationRawData, student, teacher);
        createLearningRelationship(learnRelationRawData, student, teacher);

        SkillNode skill = initSkillNode(learnRelationRawData);
        LocalDate startDate = learnRelationRawData.getStartDate();

        createIsLearningRelation(student, skill, startDate);
        createIsTeachingRelation(teacher, skill, startDate);
    }

    private void createIsTeachingRelation(TutorNode teacher, SkillNode skill, LocalDate startDate) {
        IsTeachingRelationship isTeachingRelationship = IsTeachingRelationship.builder()
                .teacher(teacher)
                .skill(skill)
                .startDate(startDate)
                .build();

        isTeachingRelationshipRepository.save(isTeachingRelationship);
    }

    private void createIsLearningRelation(TutorNode student, SkillNode skill, LocalDate startDate) {
        IsLearningRelationship isLearningRelationship = IsLearningRelationship.builder()
                .student(student)
                .skill(skill)
                .startDate(startDate)
                .build();

        isLearningRelationshipRepository.save(isLearningRelationship);
    }

    private SkillNode initSkillNode(LearnRelationRawData learnRelationRawData) {
        String skillName = learnRelationRawData.getSkill().toLowerCase();

        SkillNode skillNode;

        if (skillExists(skillName)) {
            LOGGER.info("Skill {} exists.", skillName);
            skillNode = skillNodeRepository.findByName(skillName);
        } else {
            LOGGER.info("Skill {} does not exists. Creating.", skillName);
            skillNode = SkillNode.builder()
                    .name(skillName)
                    .build();
            skillNodeRepository.save(skillNode);
        }

        return skillNode;
    }

    private boolean skillExists(String skillName) {
        return skillNodeRepository.findByName(skillName) != null;
    }

    private void createLearningRelationship(LearnRelationRawData learnRelationRawData, TutorNode student, TutorNode teacher) {
        LearningFromRelationship learningFromRelationship = LearningFromRelationship.builder()
                .skill(learnRelationRawData.getSkill())
                .skillId(learnRelationRawData.getSkillId())
                .student(student)
                .teacher(teacher)
                .build();
        LOGGER.info("Saving learning relationship with id {}", learningFromRelationship.getId());

        learningRelationshipRepository.save(learningFromRelationship);
    }

    private void createTeachingRelationship(LearnRelationRawData learnRelationRawData, TutorNode student, TutorNode teacher) {
        TeachingRelationship teachingRelationship = TeachingRelationship.builder()
                .skill(learnRelationRawData.getSkill())
                .skillId(learnRelationRawData.getSkillId())
                .student(student)
                .teacher(teacher)
                .build();
        LOGGER.info("Saving teaching relationship with id {}", teachingRelationship.getId());
        teachingRelationshipRepository.save(teachingRelationship);
    }

    private TutorNode initTeacherNode(LearnRelationRawData learnRelationRawData) {
        TutorNode teacher;
        if (!nodeExists(learnRelationRawData.getTeacherUsername())) {
            LOGGER.info("{} does not exists. Creating", learnRelationRawData.getTeacherUsername());

            teacher = TutorNode.builder()
                    .name(learnRelationRawData.getTeacherName())
                    .lastName(learnRelationRawData.getTeacherLastName())
                    .email(learnRelationRawData.getTeacherEmail())
                    .skype(learnRelationRawData.getTeacherSkype())
                    .username(learnRelationRawData.getTeacherUsername())
                    .build();
            tutorNodeRepository.save(teacher);
        } else {
            LOGGER.info("{} doeas not exists. Creating", learnRelationRawData.getTeacherUsername());

            teacher = tutorNodeRepository.findByUsername(learnRelationRawData.getTeacherUsername());
        }
        return teacher;
    }

    private TutorNode initStudentNode(LearnRelationRawData learnRelationRawData) {
        TutorNode student;
        if (!nodeExists(learnRelationRawData.getStudentUsername())) {
            LOGGER.info("{} does not exists. Creating", learnRelationRawData.getStudentUsername());
            student = TutorNode.builder()
                    .name(learnRelationRawData.getStudentName())
                    .lastName(learnRelationRawData.getStudentLastName())
                    .email(learnRelationRawData.getStudentEmail())
                    .skype(learnRelationRawData.getStudentSkype())
                    .username(learnRelationRawData.getStudentUsername())
                    .build();
            tutorNodeRepository.save(student);
        } else {
            LOGGER.info("{} does exists.", learnRelationRawData.getStudentUsername());

            student = tutorNodeRepository.findByUsername(learnRelationRawData.getStudentUsername());
        }
        return student;
    }

    private boolean nodeExists(String username) {
        LOGGER.info("Checking if node exists: {}", username);
        TutorNode tutorNode = tutorNodeRepository.findByUsername(username);

        if (tutorNode != null) {
            return true;
        }
        return false;
    }

    public TutorNode findByUsername(String username) {
        return tutorNodeRepository.findByUsername(username);
    }
}
