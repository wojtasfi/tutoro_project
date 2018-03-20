package com.recommendation.service;

import com.recommendation.dao.TeacherRecommendationRepository;
import com.recommendation.dto.RecommendedTeachersDto;
import com.recommendation.results.RecommendedTeacherResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TeachersRecommendationService {

    @Autowired
    private TeacherRecommendationRepository teacherRecommendationRepository;

    public List<RecommendedTeachersDto> getRecommendedTeachers(String tutorUsername) {

        List<RecommendedTeacherResult> results = teacherRecommendationRepository.getRecommendedTeachers(tutorUsername);

        List<RecommendedTeachersDto> recommendedTeachersDtos = new ArrayList<>();

        Map<String, List<String>> teachersWithSkills = new HashMap<>();
        for (RecommendedTeacherResult result : results) {

            String teacher = result.getTeacherUsername();
            String skillName = result.getSkillName();

            if (teachersWithSkills.get(teacher) == null) {
                teachersWithSkills.put(teacher, Collections.singletonList(skillName));
            } else {
                List<String> skills = teachersWithSkills.get(teacher);
                skills.add(skillName);
                teachersWithSkills.put(teacher, skills);
            }
        }

        for (Map.Entry<String, List<String>> entry : teachersWithSkills.entrySet()) {

            RecommendedTeachersDto dto = new RecommendedTeachersDto();
            dto.setTeacherUsername(entry.getKey());
            dto.setSkillNames(entry.getValue());
            recommendedTeachersDtos.add(dto);
        }
        return recommendedTeachersDtos;
    }
}
