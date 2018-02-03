package com.tutoro.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by wojci on 4/25/2017.
 */
@Entity
public class SkillReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Tutor teacher;

    @OneToOne
    private Tutor student;

    @ManyToOne
    private Skill skill;

    private String reviewText;
    private Date reviewDate;

    @Size(min = 1, max = 10)
    private int rate;

    public SkillReview() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tutor getTeacher() {
        return teacher;
    }

    public void setTeacher(Tutor teacher) {
        this.teacher = teacher;
    }

    public Tutor getStudent() {
        return student;
    }

    public void setStudent(Tutor student) {
        this.student = student;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }


}
