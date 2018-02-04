package com.tutoro.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    @OneToOne
    private Tutor teacher;

    @OneToOne
    private Tutor student;

    @OneToOne
    private Skill skill;

    private LocalDate startDate;
    private LocalDate endDate;

}
