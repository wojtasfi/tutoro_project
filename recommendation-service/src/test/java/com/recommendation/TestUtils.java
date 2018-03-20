package com.recommendation;


import org.neo4j.ogm.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class TestUtils {

    @Autowired
    private Session session;

    public void createTutorNode(String username) {
        Map<String, String> params = Collections.singletonMap("username", username);
        session.query("CREATE (tutor:TutorNode {username:{username}})", params);

    }

    public void createSkillNode(String skillName) {
        Map<String, String> params = Collections.singletonMap("skillName", skillName);
        session.query("CREATE (skill:SkillNode {name:{skillName}})", params);

    }

    public void createIsLearningRelation(String tutorUsername, String skillName) {

        Map<String, String> params = new HashMap<>();
        params.put("tutorUsername", tutorUsername);
        params.put("skillName", skillName);

        session.query("MATCH (tutor:TutorNode),(skill:SkillNode)" +
                "WHERE tutor.username = {tutorUsername} AND skill.name = {skillName}" +
                "CREATE (tutor)-[r:IS_LEARNING]->(skill)", params);
    }

    public void createIsTeachingRelation(String tutorUsername, String skillName) {

        Map<String, String> params = new HashMap<>();
        params.put("tutorUsername", tutorUsername);
        params.put("skillName", skillName);

        session.query("MATCH (tutor:TutorNode),(skill:SkillNode)" +
                "WHERE tutor.username = {tutorUsername} AND skill.name = {skillName}" +
                "CREATE (tutor)-[r:IS_TEACHING]->(skill)", params);
    }

    public void createLearningFromRelation(String studentUsername, String teacherUsername) {

        Map<String, String> params = new HashMap<>();
        params.put("studentUsername", studentUsername);
        params.put("teacherUsername", teacherUsername);

        session.query("MATCH (teacher:TutorNode),(student:TutorNode)" +
                "WHERE teacher.username = {teacherUsername} AND student.username = {studentUsername}" +
                "CREATE (student)-[r:LEARNING_FROM]->(teacher)", params);
    }

    public void createTeachingRelation(String studentUsername, String teacherUsername) {

        Map<String, String> params = new HashMap<>();
        params.put("studentUsername", studentUsername);
        params.put("teacherUsername", teacherUsername);

        session.query("MATCH (teacher:TutorNode),(student:TutorNode)" +
                "WHERE teacher.username = {teacherUsername} AND student.username = {studentUsername}" +
                "CREATE (teacher)-[r:TEACHING]->(student)", params);
    }

    public void clearAll() {
        session.query("MATCH (n)" +
                "DETACH DELETE n", Collections.emptyMap());
    }
}
