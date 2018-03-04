package com.tutoro;

import com.TestUtils;
import com.tutoro.dao.EmailVerificationTokenRepository;
import com.tutoro.dao.LearnRelationRepository;
import com.tutoro.dao.SkillRepository;
import com.tutoro.dao.TutorRepository;
import com.tutoro.dto.StudentDto;
import com.tutoro.dto.TeacherDto;
import com.tutoro.entities.Skill;
import com.tutoro.entities.Tutor;
import com.tutoro.service.SkillService;
import com.tutoro.service.TutorService;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@Profile("test")
public class TutorTests {

    @Autowired
    private TestUtils testUtils;

    @Autowired
    private TutorService tutorService;

    @Autowired
    private SkillService skillService;

    @Autowired
    private LearnRelationRepository relationRepository;

    @Autowired
    private EmailVerificationTokenRepository emailRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private TutorRepository tutorRepository;


    @After
    public void cleanUp() {
        relationRepository.deleteAll();
        emailRepository.deleteAll();
        skillRepository.deleteAll();
        tutorRepository.deleteAll();
    }
    @Test
    public void shouldAddSkillToTutor() {
        //given
        String skillName = "Cooking";
        String description = "Cooking dinner";

        Tutor tutor = testUtils.addTutor("Wojtek", "Figas", "wfigas");
        Skill skill = new Skill();

        skill.setName(skillName);
        skill.setDescription(description);
        //when
        tutorService.addSkill(skill, tutor);

        //then

        Skill addedSkill = testUtils.findSkillByName(skillName);
        assertNotNull(addedSkill);

        Tutor addedTutor = tutorService.findByUsername(tutor.getUsername());

        assertNotNull(addedTutor);

        assertEquals(skillName, addedSkill.getName());
        assertEquals(description, addedSkill.getDescription());

    }

    @Test
    public void shouldFindAllSkillsForTutor() {
        Tutor tutor = testUtils.addTutor("Wojtek", "Figas", "wfigas");
        testUtils.prepareRandomSkills(5, tutor.getId());

        Set<Skill> skills = skillService.getSkillByTutorUsername(tutor.getUsername());

        assertEquals(5, skills.size());
    }

    @Test
    public void shouldFindAllStudents() {
        //given
        Tutor student1 = testUtils.addTutor("studentName1", "studentLast1", "studentUser1");
        Tutor student2 = testUtils.addTutor("studentName2", "studentLast2", "studentUser2");
        Tutor student3 = testUtils.addTutor("studentName3", "studentLast3", "studentUser3");

        Tutor teacher = testUtils.addTutor("teacherName", "teacherLast", "teacherUser");

        Skill skill = testUtils.prepareSkill("TestSkill", teacher.getId());

        testUtils.createLearnRealtion(student1.getId(), teacher.getId(), skill.getId());
        testUtils.createLearnRealtion(student2.getId(), teacher.getId(), skill.getId());
        testUtils.createLearnRealtion(student3.getId(), teacher.getId(), skill.getId());

        //when
        List<StudentDto> students = tutorService.findAllStudentsWithLearningSkills(teacher.getUsername());

        assertEquals(3, students.size());

    }

    @Test
    public void shouldFindAllTeachers() {
        //given
        Tutor teacher1 = testUtils.addTutor("teacherName1", "teacherLast1", "teacherUser1");
        Tutor teacher2 = testUtils.addTutor("teacherName2", "teacherLast2", "teacherUser2");
        Tutor teacher3 = testUtils.addTutor("teacherName3", "teacherLast3", "teacherUser3");

        Tutor student = testUtils.addTutor("studentName", "studentLast", "studentUser");

        Skill skill1 = testUtils.prepareSkill("TestSkill1", teacher1.getId());
        Skill skill2 = testUtils.prepareSkill("TestSkill2", teacher2.getId());
        Skill skill3 = testUtils.prepareSkill("TestSkill3", teacher3.getId());

        testUtils.createLearnRealtion(student.getId(), teacher1.getId(), skill1.getId());
        testUtils.createLearnRealtion(student.getId(), teacher2.getId(), skill2.getId());
        testUtils.createLearnRealtion(student.getId(), teacher3.getId(), skill3.getId());

        //when
        List<Tutor> teachers = tutorService.findAllTeachers(student.getUsername());

        //then
        assertEquals(3, teachers.size());

    }

    @Test
    public void shouldFindTeacherWithSkills() {
        //given
        Tutor teacher = testUtils.addTutor("teacherName1", "teacherLast1", "teacherUser1");

        Tutor student = testUtils.addTutor("studentName", "studentLast", "studentUser");

        Skill skill1 = testUtils.prepareSkill("TestSkill1", teacher.getId());
        Skill skill2 = testUtils.prepareSkill("TestSkill2", teacher.getId());
        Skill skill3 = testUtils.prepareSkill("TestSkill3", teacher.getId());

        testUtils.createLearnRealtion(student.getId(), teacher.getId(), skill1.getId());
        testUtils.createLearnRealtion(student.getId(), teacher.getId(), skill2.getId());
        testUtils.createLearnRealtion(student.getId(), teacher.getId(), skill3.getId());

        //when
        TeacherDto teacherDto = tutorService.findTeacherByUsernameWithSkillsTeachingToStudent(teacher.getUsername(), student.getUsername());

        //then
        assertEquals(3, teacherDto.getToughtSkills().size());

    }

    @Test
    public void shouldFindStudentWithSkills() {
        //given
        Tutor teacher = testUtils.addTutor("teacherName1", "teacherLast1", "teacherUser1");

        Tutor student = testUtils.addTutor("studentName", "studentLast", "studentUser");

        Skill skill1 = testUtils.prepareSkill("TestSkill1", teacher.getId());
        Skill skill2 = testUtils.prepareSkill("TestSkill2", teacher.getId());
        Skill skill3 = testUtils.prepareSkill("TestSkill3", teacher.getId());

        testUtils.createLearnRealtion(student.getId(), teacher.getId(), skill1.getId());
        testUtils.createLearnRealtion(student.getId(), teacher.getId(), skill2.getId());
        testUtils.createLearnRealtion(student.getId(), teacher.getId(), skill3.getId());

        //when
        StudentDto studentDto = tutorService.findStudentByUsernameWithSkillsToughtByTeacher(teacher.getUsername(), student.getUsername());

        //then
        assertEquals(3, studentDto.getLearningSkills().size());

    }

}

