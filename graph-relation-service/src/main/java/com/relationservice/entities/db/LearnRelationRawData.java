package com.relationservice.entities.db;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

/**
 * Created by wojciech on 28.06.17.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

}
