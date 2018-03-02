package com.tutoro.rest;

import com.tutoro.dto.SkillForm;
import com.tutoro.entities.Skill;
import com.tutoro.service.SkillService;
import com.tutoro.service.TutorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by wojci on 4/26/2017.
 */
@RestController
@RequestMapping("/skills")
public class SkillRest {
    @Autowired
    private TutorService tutorService;

    @Autowired
    private SkillService skillService;

    private static Logger LOGGER = LoggerFactory.getLogger(SkillRest.class);

    @DeleteMapping(value = "deleteSkill")
    public ResponseEntity<String> deleteSkill(@RequestParam Long skillId) {
        Skill skill = skillService.deleteSkill(skillId);

        return ResponseEntity.ok("Deleted: " + skillId);
    }

    private String encodeUsername(Skill skill) {
        String encodedUsername = null;
        try {
            encodedUsername = URLEncoder.encode(skill.getTutor().getUsername(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("Could not encode message", e);
        }
        return encodedUsername;
    }

    @PostMapping(value = "addskill")
    public ResponseEntity<String> addSkill(@ModelAttribute SkillForm skillForm,
                                           Model model) {

        Skill skill = skillService.getSkillById(skillForm.getSkillId());
        skill.setName(skillForm.getName());
        skill.setDescription(skillForm.getDescription());

        String[] stringTags = skillForm.getTags().split(",");
        Set<String> tags = new HashSet<>(Arrays.asList(stringTags));
        skill.setTags(tags);
        LOGGER.info(skill.toString());

        tutorService.addSkill(skill, skill.getTutor());
//        skillService.saveSkill(skill);

        return ResponseEntity.created(URI.create("/skills/skill" + skill.getId())).build();

    }

    @GetMapping(value = "skill/{skillId}")
    public Skill viewSkill(@PathVariable Long skillId) {
        return skillService.getSkillById(skillId);
    }
}