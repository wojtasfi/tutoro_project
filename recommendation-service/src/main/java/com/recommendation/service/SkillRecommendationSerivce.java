package com.recommendation.service;

import com.recommendation.dao.SkillRecommendationRepository;
import com.recommendation.dto.RecommendedSkillsDto;
import com.recommendation.results.RecommendedSkillResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SkillRecommendationSerivce {

    @Autowired
    private SkillRecommendationRepository skillRecommendationRepository;

    public RecommendedSkillsDto getRecommendedSkills(String tutorUsername) {
        List<RecommendedSkillResult> results = skillRecommendationRepository.findRecommendedSkills(tutorUsername);

        List<String> skillNames = results.stream().map(RecommendedSkillResult::getSkillName).collect(Collectors.toList());

        return new RecommendedSkillsDto(skillNames);
    }
}
