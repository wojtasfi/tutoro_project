package com.tutoro.rest;

import com.tutoro.entities.LearnRelation;
import com.tutoro.entities.Skill;
import com.tutoro.entities.Tutor;
import com.tutoro.service.LearnRelationService;
import com.tutoro.service.SkillService;
import com.tutoro.service.TutorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.time.LocalDate;

/**
 * Created by wojci on 5/23/2017.
 */
@RestController
@RequestMapping("/learn_relations")
public class LearnRelationRest {

    private static Logger LOGGER = LoggerFactory.getLogger(LearnRelationRest.class);
    @Autowired
    private TutorService tutorService;

    @Autowired
    private SkillService skillService;

    @Autowired
    private LearnRelationService learnRelationService;


    @PostMapping(value = "/save")
    private ResponseEntity<String> saveLearnRelation(@RequestParam String studentName,
                                                     @RequestParam String techareUsername,
                                                     @RequestParam Long skillId) {

        Skill skill = skillService.getSkillById(skillId);
        Tutor teacher = tutorService.findByUsername(techareUsername);
        Tutor student = tutorService.findByUsername(studentName);

        if (learnRelationService.learnRelationExists(skill, teacher, student)) {
            return ResponseEntity.badRequest().body("Relationship already exists");
        }

        LearnRelation learnRelation = LearnRelation.builder()
                .teacherId(teacher.getId())
                .studentId(student.getId())
                .skillId(skill.getId())
                .startDate(LocalDate.now())
                .endDate(null)
                .build();

        learnRelationService.saveLearnRelation(learnRelation);
        String encodedUsername = null;
        try {
            encodedUsername = URLEncoder.encode(student.getUsername(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("Could not encode message", e);
        }
        return ResponseEntity.created(URI.create("/learn_relations" + learnRelation.getId())).build();

    }


    @GetMapping("/relation/{relationId}")
    private LearnRelation getLearnRelation(@PathVariable Long relationId) {
        return learnRelationService.getById(relationId);
    }

}
