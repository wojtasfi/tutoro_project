package com.tutoro.dto;

import com.tutoro.entities.LearnRelation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Created by wojciech on 28.06.17.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LearnRelationRawDataDTO {
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


    public static LearnRelationRawDataDTO createFromLearnRelation(LearnRelation learnRelation) {
        LearnRelationRawDataDTO dto = new LearnRelationRawDataDTO();
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

        dto.startDate = learnRelation.getStartDate();
        dto.endDate = learnRelation.getEndDate();

        return dto;
    }
}
