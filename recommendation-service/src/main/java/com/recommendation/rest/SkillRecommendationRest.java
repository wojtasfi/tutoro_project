package com.recommendation.rest;

import com.recommendation.dto.RecommendedSkillsDto;
import com.recommendation.service.SkillRecommendationSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("skills/")
public class SkillRecommendationRest {


    @Autowired
    private SkillRecommendationSerivce skillRecommendationSerivce;

    @GetMapping()
    public RecommendedSkillsDto getRecommendedSkills(@RequestParam String tutorUsername) {

        return skillRecommendationSerivce.getRecommendedSkills(tutorUsername);

    }
}
