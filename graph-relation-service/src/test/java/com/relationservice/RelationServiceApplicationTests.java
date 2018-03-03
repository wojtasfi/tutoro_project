package com.relationservice;

import com.relationservice.dao.neo4j.*;
import com.relationservice.entities.db.LearnRelationRawData;
import com.relationservice.entities.neo4j.*;
import com.relationservice.service.SkillNodeService;
import com.relationservice.service.TutorNodeService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@Import({TestUtils.class, TutorNodeService.class, SkillNodeService.class})
public class RelationServiceApplicationTests {


    @Autowired
    private TestUtils testUtils;

    @Autowired
    private TutorNodeService tutorNodeService;

    @Autowired
    private SkillNodeService skillNodeService;

    @Autowired
    private IsLearningRelationshipRepository isLearningRepository;

    @Autowired
    private IsTeachingRelationshipRepository isTeachingRepository;

    @Autowired
    private LearningRelationshipRepository learningRelRepository;

    @Autowired
    private TeachingRelationshipRepository teachingRelRepository;

    @Autowired
    private TutorNodeRepository tutorNodeRepository;

    @Autowired
    private SkillNodeRepository skillNodeRepository;

    //lower case
    private String skillName = "cooking";
    private String studentUsername = "andrew";
    private String teacherUsername = "adam";

    @Before
    public void prepareData() {
        LearnRelationRawData rawData = testUtils.createLearnRelationRawData(skillName, studentUsername, teacherUsername);
        tutorNodeService.processLearnRelationRawData(rawData);
    }

    @After
    public void cleanUp() {
        tutorNodeRepository.deleteAll();
        skillNodeRepository.deleteAll();
        isLearningRepository.deleteAll();
        isTeachingRepository.deleteAll();
        learningRelRepository.deleteAll();
        teachingRelRepository.deleteAll();
    }

    @Test
    public void shouldCreateNodes() {

        SkillNode skillNode = skillNodeService.findByName(skillName);
        TutorNode teacherNode = tutorNodeService.findByUsername(teacherUsername);
        TutorNode studentNode = tutorNodeService.findByUsername(studentUsername);

        assertNotNull(skillNode);
        assertNotNull(teacherNode);
        assertNotNull(studentNode);
    }

    @Test
    public void shouldCreateTeachingAndLearningFromRelationships() {
        List<LearningFromRelationship> learningList = learningRelRepository.findBySkill(skillName).stream()
                .filter(rel ->
                        rel.getStudent().getUsername().equals(studentUsername) &&
                                rel.getTeacher().getUsername().equals(teacherUsername)
                )
                .collect(Collectors.toList());


        List<TeachingRelationship> teachingList = teachingRelRepository.findBySkill(skillName).stream()
                .filter(rel ->
                        rel.getStudent().getUsername().equals(studentUsername) &&
                                rel.getTeacher().getUsername().equals(teacherUsername)
                )
                .collect(Collectors.toList());

        assertEquals(1, learningList.size());
        assertEquals(1, teachingList.size());

    }

    @Test
    public void shouldCreateIsTeachingAndIsLearningRelationships() {

        SkillNode skillNode = skillNodeService.findByName(skillName);
        Iterator<IsTeachingRelationship> isTeachingIterator = isTeachingRepository.findAll().iterator();

        IsTeachingRelationship isTeachingRelationship = null;
        if (isTeachingIterator.hasNext()) {
            isTeachingRelationship = isTeachingIterator.next();
        }

        Iterator<IsLearningRelationship> isLearningIterator = isLearningRepository.findAll().iterator();

        IsLearningRelationship isLearningRelationship = null;
        if (isLearningIterator.hasNext()) {
            isLearningRelationship = isLearningIterator.next();
        }

        assertNotNull(isLearningRelationship);
        assertNotNull(isTeachingRelationship);

        TutorNode teacher = isTeachingRelationship.getTeacher();
        TutorNode student = isLearningRelationship.getStudent();

        assertNotNull(teacher);
        assertNotNull(student);

        assertEquals(teacherUsername, teacher.getUsername());
        assertEquals(studentUsername, student.getUsername());
    }

}
