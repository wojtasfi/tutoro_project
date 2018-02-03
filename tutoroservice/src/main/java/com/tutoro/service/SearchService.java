package com.tutoro.service;

import com.tutoro.dao.SkillRepository;
import com.tutoro.dao.TutorRepository;
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
 * Created by wojci on 5/18/2017.
 */
@Service
public class SearchService {
    private static Logger LOGGER = LoggerFactory.getLogger(SearchService.class);
    @Autowired
    TutorRepository tutorRepository;

    @Autowired
    SkillRepository skillRepository;

    public List<Tutor> simpleSearch(String text) {
        String[] words = text.toLowerCase().split(" ");
        List<Tutor> tutors = (List<Tutor>) tutorRepository.findAll();

        List<Tutor> foundTutors = new ArrayList<>();

        //find entire text
        for (Tutor tutor : tutors) {
            Set<Skill> skills = tutor.getSkills();
            Set<Skill> searchedSkills = new HashSet<>();

            for (Skill skill : skills) {
                searchSkill(words, searchedSkills, skill);
            }

            if (!searchedSkills.isEmpty()) {
                LOGGER.info("not empty");
                foundTutors.add(tutor);
            }
        }

        return foundTutors;
    }

    private void searchSkill(String[] words, Set<Skill> searchedSkills, Skill skill) {
        for (String word : words) {
            if (skill.getName().toLowerCase().equals(word)) {
                LOGGER.info("name ok");
                searchedSkills.add(skill);
            } else {
                Set<String> tags = skill.getTags();
                for (String tag : tags) {
                    if (tag.toLowerCase().equals(word)) {
                        LOGGER.info("tag ok");
                        searchedSkills.add(skill);
                        break;
                    }
                }
            }
        }
    }
}
