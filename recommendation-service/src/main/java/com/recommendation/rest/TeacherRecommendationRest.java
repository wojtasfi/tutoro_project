package com.recommendation.rest;

import com.recommendation.dto.RecommendedTeachersDto;
import com.recommendation.service.TeachersRecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("teachers/")
public class TeacherRecommendationRest {


    @Autowired
    private TeachersRecommendationService teachersRecommendationService;


    @GetMapping()
    public List<RecommendedTeachersDto> getRecommendedTeachers(@RequestParam String tutorUsername) {
        return teachersRecommendationService.getRecommendedTeachers(tutorUsername);
    }
}
