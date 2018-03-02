package com.tutoro.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by wojci on 4/25/2017.
 */
@Entity
@Data
@ToString
public class SkillReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Tutor teacher;

    @OneToOne
    private Tutor student;

    private Long skillId;

    private String reviewText;
    private Date reviewDate;

    @Size(min = 1, max = 10)
    private int rate;

    public SkillReview() {
    }


}
