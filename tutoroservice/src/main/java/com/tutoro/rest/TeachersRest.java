package com.tutoro.rest;

import com.tutoro.dto.TeacherDto;
import com.tutoro.entities.Tutor;
import com.tutoro.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("teachers/")
public class TeachersRest {

    @Autowired
    private TutorService tutorService;


    @GetMapping()
    public List<Tutor> showTeachers(@RequestParam String username) {
        return tutorService.findAllTeachers(username);
    }

    @GetMapping(value = "teacher")
    public TeacherDto showTeacher(@RequestParam String teacherUsername,
                                  @RequestParam String studentUsername) {
        return tutorService.findTeacherByUsernameWithSkillsTeachingToStudent(teacherUsername, studentUsername);
    }
}
