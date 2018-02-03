package com.tutoro.service;

import com.tutoro.dao.SkillRepository;
import com.tutoro.dao.TutorRepository;
import com.tutoro.entities.LearnRelation;
import com.tutoro.entities.Skill;
import com.tutoro.entities.Tutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by wojci on 4/16/2017.
 */

@Service
public class TutorService {

    @Autowired
    TutorRepository tutorRepository;

    @Autowired
    SkillRepository skillRepository;

    public Tutor saveTutor(Tutor tutor) {
        tutorRepository.save(tutor);
        return tutor;
    }


    public Tutor findByLogin(String username) {
        Tutor tutor = tutorRepository.findByUsername(username);
        return tutor;
    }

    public void addSkill(Skill skill, Tutor tutor) {
        tutor.addSkill(skill);
        tutorRepository.save(tutor);
    }

    public boolean checkIfTutorExists(String username) {
        Tutor tutor = tutorRepository.findByUsername(username);
        if (tutor == null) {
            return false;
        }
        return true;
    }

    public Tutor findByUsername(String username) {
        Tutor tutor = tutorRepository.findByUsername(username);
        return tutor;
    }

    public List<Tutor> findAll() {
        return (List<Tutor>) tutorRepository.findAll();
    }

    public Tutor findOne(Long id) {
        return tutorRepository.findOne(id);
    }

    public List<Tutor> findAllTeachers(String tutor) {
        List<Tutor> teachers = new ArrayList<>();
        Set<LearnRelation> relations = tutorRepository.findByUsername(tutor).getStudentRelations();

        for (LearnRelation relation : relations) {
            if (!teachers.contains(relation.getTeacher())) {
                teachers.add(relation.getTeacher());
            }
        }

        return teachers;
    }

    public Tutor findByUsernameWithSkillsTeachingToStudent(String teacher, String student) {
        Tutor teacherTutor = tutorRepository.findByUsername(teacher);
        Tutor studentTutor = tutorRepository.findByUsername(student);

        Set<Skill> toughtSkills = new HashSet<>();

        for (LearnRelation relation : teacherTutor.getTeacherRelations()) {
            if (relation.getStudent().equals(studentTutor)) {
                toughtSkills.add(relation.getSkill());
            }
        }

        teacherTutor.setSkills(toughtSkills);

        return teacherTutor;
    }

    public Tutor findByUsernameWithSkillsToughtByTeacher(String teacher, String student) {
        Tutor teacherTutor = tutorRepository.findByUsername(teacher);
        Tutor studentTutor = tutorRepository.findByUsername(student);

        Set<Skill> toughtSkills = new HashSet<>();

        for (LearnRelation relation : studentTutor.getStudentRelations()) {
            if (relation.getTeacher().equals(teacherTutor)) {
                toughtSkills.add(relation.getSkill());
            }
        }

        teacherTutor.setSkills(toughtSkills);

        return teacherTutor;
    }

    private List<Tutor> findAllStudents(String username) {
        List<Tutor> students = new ArrayList<>();
        Set<LearnRelation> relations = tutorRepository.findByUsername(username).getTeacherRelations();

        for (LearnRelation relation : relations) {
            if (!students.contains(relation.getStudent())) {
                students.add(relation.getStudent());
            }
        }

        return students;
    }

    public List<Tutor> findAllStudentsWithLearningSkills(String username) {
        List<Tutor> students = findAllStudents(username);

        for (Tutor student : students) {
            Set<Skill> studentSkills = new HashSet<>();
            for (LearnRelation relation : student.getStudentRelations()) {
                if (relation.getTeacher().getUsername().equals(username)) {
                    studentSkills.add(relation.getSkill());
                }
            }
            student.setSkills(studentSkills);
        }

        return students;
    }
}
