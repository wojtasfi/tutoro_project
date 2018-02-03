package com.tutoro.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by wojci on 5/23/2017.
 */
@Entity
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

    @Override
    public String toString() {
        return "LearnRelation{" +
                "id=" + id +
                ", teacher=" + teacher.getUsername() +
                ", student=" + student.getUsername() +
                ", skill=" + skill.getName() +
                '}';
    }
}
