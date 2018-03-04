package com.tutoro.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tutoro.entities.LearnRelationRawData;
import lombok.*;

import java.time.LocalDate;

/**
 * Created by wojciech on 28.06.17.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
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


    public static LearnRelationRawDataDTO createFromRawData(LearnRelationRawData rawData) {
        LearnRelationRawDataDTO dto = new LearnRelationRawDataDTO();

        dto.teacherId = rawData.getTeacherId();
        dto.teacherName = rawData.getTeacherName();
        dto.teacherLastName = rawData.getTeacherLastName();
        dto.teacherUsername = rawData.getTeacherUsername();
        dto.teacherEmail = rawData.getTeacherEmail();
        dto.teacherSkype = rawData.getTeacherSkype();

        dto.studentId = rawData.getStudentId();
        dto.studentName = rawData.getStudentName();
        dto.studentLastName = rawData.getStudentLastName();
        dto.studentUsername = rawData.getStudentUsername();
        dto.studentEmail = rawData.getStudentEmail();
        dto.studentSkype = rawData.getStudentSkype();

        dto.skill = rawData.getSkill();
        dto.skillId = rawData.getSkillId();

        dto.startDate = rawData.getStartDate();
        dto.endDate = rawData.getEndDate();

        return dto;
    }

}
