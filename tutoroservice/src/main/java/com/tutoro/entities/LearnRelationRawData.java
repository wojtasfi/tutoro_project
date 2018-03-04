package com.tutoro.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by wojciech on 28.06.17.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "learn_relation_raw_data")
public class LearnRelationRawData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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

    private LocalDate startDate;
    private LocalDate endDate;

    public static LearnRelationRawData createFromLearnRelation(LearnRelation newLearnRelation, Tutor student, Tutor teacher, Skill skill) {
        LearnRelationRawData rawData = new LearnRelationRawData();
        rawData.teacherId = teacher.getId();
        rawData.teacherName = teacher.getName();
        rawData.teacherLastName = teacher.getLastName();
        rawData.teacherUsername = teacher.getUsername();
        rawData.teacherEmail = teacher.getEmail();
        rawData.teacherSkype = teacher.getSkype();

        rawData.studentId = student.getId();
        rawData.studentName = student.getName();
        rawData.studentLastName = student.getLastName();
        rawData.studentUsername = student.getUsername();
        rawData.studentEmail = student.getEmail();
        rawData.studentSkype = student.getSkype();

        rawData.skill = skill.getName();
        rawData.skillId = skill.getId();

        rawData.startDate = newLearnRelation.getStartDate();
        rawData.endDate = newLearnRelation.getEndDate();

        return rawData;

    }
}
