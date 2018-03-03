package com.tutoro.rest;

import com.tutoro.dto.TutorDto;
import com.tutoro.entities.Skill;
import com.tutoro.entities.Tutor;
import com.tutoro.service.SearchService;
import com.tutoro.service.SkillService;
import com.tutoro.service.TutorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by wojci on 4/26/2017.
 */
@RestController
@RequestMapping("search/")
public class SearchRest {

    @Autowired
    private TutorService tutorService;

    @Autowired
    private SearchService searchService;

    @Autowired
    private SkillService skillService;

    private static Logger LOGGER = LoggerFactory.getLogger(SearchRest.class);

    @GetMapping(value = "tutors")
    public List<TutorDto> findAllTutors() {

        List<Tutor> tutors = tutorService.findAll();
        List<TutorDto> dtos = new ArrayList<>();

        for (Tutor tutor : tutors) {

            Set<Skill> limitedSkills = skillService.getSkillByTutorId(tutor.getId())
                    .stream()
                    .limit(15)
                    .collect(Collectors.toSet());

            TutorDto tutorDto = TutorDto.builder()
                    .id(tutor.getId())
                    .userName(tutor.getUsername())
                    .skills(limitedSkills)
                    .build();
            dtos.add(tutorDto);

        }

        return dtos;
    }

    @RequestMapping(value = "skill", method = POST)
    public List<Tutor> skillSearch(@RequestParam String text, Model model) {
        return searchService.simpleSearch(text);
    }

}
