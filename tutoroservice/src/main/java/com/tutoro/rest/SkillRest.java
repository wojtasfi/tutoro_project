package com.tutoro.rest;

import com.tutoro.dto.SkillForm;
import com.tutoro.entities.Skill;
import com.tutoro.entities.Tutor;
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
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by wojci on 4/26/2017.
 */
@RestController
@RequestMapping("skill/")
public class SkillRest {
    @Autowired
    private TutorService tutorService;

    @Autowired
    private SkillService skillService;

    private static Logger LOGGER = LoggerFactory.getLogger(SkillRest.class);

    @DeleteMapping("deleteSkill")
    public ResponseEntity<String> deleteSkill(@RequestParam Long skillId) {
        Skill skill = skillService.deleteSkill(skillId);

        return ResponseEntity.ok("Deleted: " + skillId);
    }

    private String encodeUsername(Skill skill) {
        String encodedUsername = null;
        Tutor tutor = tutorService.findOne(skill.getTutorId());
        try {
            encodedUsername = URLEncoder.encode(tutor.getUsername(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("Could not encode message", e);
        }
        return encodedUsername;
    }

    @PostMapping("addSkill")
    public ResponseEntity<String> addSkill(@RequestParam SkillForm skillForm) {

        Skill skill = new Skill();
        skill.setName(skillForm.getName());
        skill.setDescription(skillForm.getDescription());
        skill.setTutorId(skillForm.getTutorId());

        String[] stringTags = skillForm.getTags().split(",");
        Set<String> tags = new HashSet<>(Arrays.asList(stringTags));
        skill.setTags(tags);

        LOGGER.info("Adding skill <{}> to tutor <{}>", skill.getName(), skill.getTutorId());
        skillService.saveSkill(skill);

        return ResponseEntity.created(URI.create("/skills/skill" + skill.getId())).build();

    }

    @GetMapping("findBySkillId")
    public Skill viewSkill(@RequestParam Long skillId) {
        return skillService.getSkillById(skillId);
    }

    @GetMapping("findByTutorUsername")
    public Set<Skill> findSkillsByTutorUsername(@RequestParam String tutorUsername) {
        return skillService.getSkillByTutorUsername(tutorUsername);
    }

    @GetMapping("findByTutorId")
    public Set<Skill> findSkillsByTutorId(@RequestParam Long tutorId) {
        return skillService.getSkillByTutorId(tutorId);
    }
}