package com.tutoro.rest;

import com.tutoro.dto.StudentDto;
import com.tutoro.service.TutorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("students/")
public class StudentsRest {

    private static Logger LOGGER = LoggerFactory.getLogger(StudentsRest.class);
    @Autowired
    private TutorService tutorService;


    @GetMapping()
    public List<StudentDto> getStudents(@RequestParam String username) {
        return tutorService.findAllStudentsWithLearningSkills(username);


    }

    @GetMapping(value = "student")
    public StudentDto getStudent(@RequestParam String teacher, @RequestParam String student) {
        return tutorService.findStudentByUsernameWithSkillsToughtByTeacher(teacher, student);

    }
}
