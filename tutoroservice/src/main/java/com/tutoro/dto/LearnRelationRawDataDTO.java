package com.tutoro.dto;

import com.tutoro.entities.LearnRelation;
import com.tutoro.entities.Skill;
import com.tutoro.entities.Tutor;
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


    public static LearnRelationRawDataDTO createFromLearnRelation(LearnRelation learnRelation, Tutor student, Tutor teacher, Skill skill) {
        LearnRelationRawDataDTO dto = new LearnRelationRawDataDTO();
        dto.teacherId = teacher.getId();
        dto.teacherName = teacher.getName();
        dto.teacherLastName = teacher.getLastName();
        dto.teacherUsername = teacher.getUsername();
        dto.teacherEmail = teacher.getEmail();
        dto.teacherSkype = teacher.getSkype();

        dto.studentId = student.getId();
        dto.studentName = student.getName();
        dto.studentLastName = student.getLastName();
        dto.studentUsername = student.getUsername();
        dto.studentEmail = student.getEmail();
        dto.studentSkype = student.getSkype();

        dto.skill = skill.getName();
        dto.skillId = skill.getId();

        dto.startDate = learnRelation.getStartDate();
        dto.endDate = learnRelation.getEndDate();

        return dto;
    }
}
