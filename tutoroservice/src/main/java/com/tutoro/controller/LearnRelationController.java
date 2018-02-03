package com.tutoro.controller;

import com.tutoro.entities.LearnRelation;
import com.tutoro.entities.Skill;
import com.tutoro.entities.Tutor;
import com.tutoro.service.LearnRelationService;
import com.tutoro.service.SkillService;
import com.tutoro.service.TutorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by wojci on 5/23/2017.
 */
@Controller
@RequestMapping("/relations")
public class LearnRelationController {

    private static Logger LOGGER = LoggerFactory.getLogger(LearnRelationController.class);
    @Autowired
    private TutorService tutorService;

    @Autowired
    private SkillService skillService;

    @Autowired
    private LearnRelationService learnRelationService;


    @RequestMapping(value = "/save", method = GET)
    private String saveLearnRelation(@RequestParam String studentName,
                                     @RequestParam Long skillId,
                                     Model model) {

        Skill skill = skillService.getSkillById(skillId);
        Tutor teacher = tutorService.findOne(skill.getTutor().getId());
        Tutor student = tutorService.findByUsername(studentName);

        LearnRelation learnRelation = new LearnRelation();
        learnRelation.setTeacher(teacher);
        learnRelation.setStudent(student);
        learnRelation.setSkill(skill);

        learnRelationService.saveLearnRelation(learnRelation);
        model.addAttribute("tutor", student);
        String encodedUsername = null;
        try {
            encodedUsername = URLEncoder.encode(student.getUsername(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("Could not encode message", e);
        }
        return "redirect:/tutor/profile/" + encodedUsername;

    }


    @RequestMapping(value = "/showTeachers", method = RequestMethod.GET)
    public String showTeachers(@RequestParam String username, Model model) {
        List<Tutor> teachers = tutorService.findAllTeachers(username);

        Tutor tutor = tutorService.findByUsername(username);

        model.addAttribute("teachers", teachers);
        model.addAttribute("tutor", tutor);

        return "teachers";

    }

    @RequestMapping(value = "/showTeacher", method = RequestMethod.GET)
    public String showTeacher(@RequestParam String teacher, @RequestParam String student, Model model) {
        Tutor teacherTutor = tutorService.findByUsernameWithSkillsTeachingToStudent(teacher, student);
        Tutor tutor = tutorService.findByUsername(student);

        model.addAttribute("teacher", teacherTutor);
        model.addAttribute("tutor", tutor);

        return "teacher";

    }

    @RequestMapping(value = "/showStudents", method = RequestMethod.GET)
    public String showStudents(@RequestParam String username, Model model) {
        List<Tutor> students = tutorService.findAllStudentsWithLearningSkills(username);
        LOGGER.info("Teacher: " + username);
        Tutor tutor = tutorService.findByUsername(username);

        model.addAttribute("students", students);
        model.addAttribute("tutor", tutor);

        return "students";

    }

    @RequestMapping(value = "/showStudent", method = RequestMethod.GET)
    public String showStudent(@RequestParam String teacher, @RequestParam String student, Model model) {

        LOGGER.info("Teacher: " + teacher);
        LOGGER.info("Student: " + student);

        Tutor tutor = tutorService.findByUsernameWithSkillsToughtByTeacher(teacher, student);
        Tutor studentTutor = tutorService.findByUsername(student);

        model.addAttribute("student", studentTutor);
        model.addAttribute("tutor", tutor);

        return "student";

    }

}
