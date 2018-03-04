package com.relationservice.dto;

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

}
