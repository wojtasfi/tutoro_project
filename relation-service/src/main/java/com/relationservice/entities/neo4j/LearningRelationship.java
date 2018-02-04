package com.relationservice.entities.neo4j;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 * Created by wojciech on 09.07.17.
 */
@RelationshipEntity(type = "LEARNING_FROM")
public class LearningRelationship {

    @GraphId
    private Long id;
    @EndNode
    private TutorNode teacher;
    @StartNode
    private TutorNode student;

    private String skill;
    private Long skillId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    public TutorNode getTeacher() {
        return teacher;
    }

    public void setTeacher(TutorNode teacher) {
        this.teacher = teacher;
    }

    public TutorNode getStudent() {
        return student;
    }

    public void setStudent(TutorNode student) {
        this.student = student;
    }
}
