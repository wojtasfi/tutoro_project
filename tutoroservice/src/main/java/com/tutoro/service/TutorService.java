package com.tutoro.service;

import com.tutoro.dao.LearnRelationRepository;
import com.tutoro.dao.SkillRepository;
import com.tutoro.dao.TutorRepository;
import com.tutoro.dto.StudentDto;
import com.tutoro.dto.TeacherDto;
import com.tutoro.entities.LearnRelation;
import com.tutoro.entities.Skill;
import com.tutoro.entities.Tutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static Logger LOGGER = LoggerFactory.getLogger(TutorService.class);

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private LearnRelationRepository learnRelationRepository;

    public Tutor saveTutor(Tutor tutor) {
        tutorRepository.save(tutor);
        return tutor;
    }


    public Tutor findByLogin(String username) {
        Tutor tutor = tutorRepository.findByUsername(username);
        return tutor;
    }

    public void addSkill(Skill skill, Tutor tutor) {
        LOGGER.info("Adding skill <{}> to tutor <{}>", skill.getName(), tutor.getUsername());
        skillRepository.save(skill);
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

    public List<Tutor> findAllTeachers(String username) {
        List<Tutor> teachers = new ArrayList<>();
        Tutor tutor = tutorRepository.findByUsername(username);
        Set<LearnRelation> relations = learnRelationRepository.findByStudentId(tutor.getId());

        for (LearnRelation relation : relations) {
            Tutor teacher = tutorRepository.findOne(relation.getTeacherId());
            if (!teachers.contains(teacher)) {
                teachers.add(teacher);
            }
        }

        return teachers;
    }

    public TeacherDto findTeacherByUsernameWithSkillsTeachingToStudent(String teacherUsername, String studentUsername) {
        Tutor teacherTutor = tutorRepository.findByUsername(teacherUsername);
        Tutor studentTutor = tutorRepository.findByUsername(studentUsername);

        if (teacherTutor == null || studentTutor == null) {
            return null;
        }
        Set<Skill> toughtSkills = new HashSet<>();

        Set<LearnRelation> teacherRelations = learnRelationRepository.findByTeacherId(teacherTutor.getId());

        for (LearnRelation relation : teacherRelations) {
            Tutor student = tutorRepository.findOne(relation.getStudentId());

            if (student.equals(studentTutor)) {
                toughtSkills.add(skillRepository.getSkillById(relation.getSkillId()));
            }
        }

        TeacherDto teacherDto = TeacherDto.builder()
                .id(teacherTutor.getId())
                .name(teacherTutor.getName())
                .lastName(teacherTutor.getLastName())
                .build();

        teacherDto.setToughtSkills(toughtSkills);

        return teacherDto;
    }

    public StudentDto findStudentByUsernameWithSkillsToughtByTeacher(String teacherUsername, String studentUsername) {
        Tutor teacherTutor = tutorRepository.findByUsername(teacherUsername);
        Tutor studentTutor = tutorRepository.findByUsername(studentUsername);

        if (teacherTutor == null || studentTutor == null) {
            return null;
        }
        Set<Skill> learningSkills = new HashSet<>();

        Set<LearnRelation> studentRelations = learnRelationRepository.findByStudentId(studentTutor.getId());

        for (LearnRelation relation : studentRelations) {

            Tutor teacher = tutorRepository.findOne(relation.getTeacherId());

            if (teacher.equals(teacherTutor)) {

                learningSkills.add(skillRepository.getSkillById(relation.getSkillId()));
            }
        }

        StudentDto studentDto = StudentDto.builder()
                .id(teacherTutor.getId())
                .name(teacherTutor.getName())
                .lastName(teacherTutor.getLastName())
                .build();

        studentDto.setLearningSkills(learningSkills);

        return studentDto;
    }

    private List<Tutor> findAllStudents(String username) {
        List<Tutor> students = new ArrayList<>();
        Tutor teacher = tutorRepository.findByUsername(username);
        Set<LearnRelation> relations = learnRelationRepository.findByTeacherId(teacher.getId());

        for (LearnRelation relation : relations) {
            Tutor student = tutorRepository.findOne(relation.getStudentId());
            if (!students.contains(student)) {
                students.add(student);
            }
        }

        return students;
    }

    public List<StudentDto> findAllStudentsWithLearningSkills(String username) {
        List<Tutor> studentsTutors = findAllStudents(username);
        List<StudentDto> students = new ArrayList<>();

        for (Tutor student : studentsTutors) {
            Set<Skill> studentSkills = new HashSet<>();

            for (LearnRelation relation : learnRelationRepository.findByStudentId(student.getId())) {

                Tutor teacher = tutorRepository.findOne(relation.getTeacherId());
                if (teacher.getUsername().equals(username)) {
                    studentSkills.add(skillRepository.getSkillById(relation.getSkillId()));
                }
            }
            StudentDto studentDto = StudentDto.builder()
                    .id(student.getId())
                    .name(student.getName())
                    .lastName(student.getLastName())
                    .build();

            studentDto.setLearningSkills(studentSkills);
            students.add(studentDto);
        }

        return students;
    }

    public boolean checkIfEmailExists(String email) {
        Tutor tutor = tutorRepository.findByEmail(email);
        if (tutor == null) {
            return false;
        }
        return true;
    }
}
