package com.tutoro.dto;

import com.tutoro.entities.LearnRelation;

/**
 * Created by wojciech on 28.06.17.
 */
public class LearnRelationDTO {
    private Long teacherId;
    private String teacherName;
    private String teacherLastName;
    private String teacherUsername;
    private String teacherEmail;
    private String teacherSkype;

    private Long studentId;
    private String studentName;
    private String studentLastName;
    private String studentUsername;
    private String studentEmail;
    private String studentSkype;
    private String skill;
    private Long skillId;


    public static LearnRelationDTO createFromLearnRelation(LearnRelation learnRelation) {
        LearnRelationDTO dto = new LearnRelationDTO();
        dto.teacherId = learnRelation.getTeacher().getId();
        dto.teacherName = learnRelation.getTeacher().getName();
        dto.teacherLastName = learnRelation.getTeacher().getLastName();
        dto.teacherUsername = learnRelation.getTeacher().getUsername();
        dto.teacherEmail = learnRelation.getTeacher().getEmail();
        dto.teacherSkype = learnRelation.getTeacher().getSkype();

        dto.studentId = learnRelation.getStudent().getId();
        dto.studentName = learnRelation.getStudent().getName();
        dto.studentLastName = learnRelation.getStudent().getLastName();
        dto.studentUsername = learnRelation.getStudent().getUsername();
        dto.studentEmail = learnRelation.getStudent().getEmail();
        dto.studentSkype = learnRelation.getStudent().getSkype();

        dto.skill = learnRelation.getSkill().getName();
        dto.skillId = learnRelation.getSkill().getId();

        return dto;
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

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherLastName() {
        return teacherLastName;
    }

    public void setTeacherLastName(String teacherLastName) {
        this.teacherLastName = teacherLastName;
    }

    public String getTeacherUsername() {
        return teacherUsername;
    }

    public void setTeacherUsername(String teacherUsername) {
        this.teacherUsername = teacherUsername;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    public String getTeacherSkype() {
        return teacherSkype;
    }

    public void setTeacherSkype(String teacherSkype) {
        this.teacherSkype = teacherSkype;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentLastName() {
        return studentLastName;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
    }

    public String getStudentUsername() {
        return studentUsername;
    }

    public void setStudentUsername(String studentUsername) {
        this.studentUsername = studentUsername;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentSkype() {
        return studentSkype;
    }

    public void setStudentSkype(String studentSkype) {
        this.studentSkype = studentSkype;
    }


}
