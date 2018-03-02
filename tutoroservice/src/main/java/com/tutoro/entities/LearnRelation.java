package com.tutoro.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by wojci on 5/23/2017.
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LearnRelation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long teacherId;

    private Long studentId;

    private Long skillId;

    private LocalDate startDate;
    private LocalDate endDate;

}
