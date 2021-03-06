package com.tutoro.rest;

import com.tutoro.dto.TutorDto;
import com.tutoro.entities.Tutor;
import com.tutoro.service.SkillService;
import com.tutoro.service.TutorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by wojci on 4/26/2017.
 */
@RestController
@RequestMapping("tutor/")
public class TutorRest {

    @Autowired
    private TutorService tutorService;

    @Autowired
    private SkillService skillService;

    private static Logger LOGGER = LoggerFactory.getLogger(TutorRest.class);

    @GetMapping(value = "/{username}")
    public TutorDto getTutor(@PathVariable("username") String username) {

        Tutor tutor = tutorService.findByUsername(username);
//        cleanSkills(tutor);

        return TutorDto.builder()
                .id(tutor.getId())
                .userName(tutor.getUsername()).build();

    }

    @PutMapping(value = "/edit")
    public ResponseEntity<String> editProfile(@RequestBody Tutor tutor) {

        Tutor oldTutor = tutorService.findByUsername(tutor.getUsername());

        tutor.setPassword(oldTutor.getPassword());
        tutor.setProfilePic(oldTutor.getProfilePic());
        tutorService.saveTutor(tutor);

        String encodedUsername = null;
        try {
            encodedUsername = URLEncoder.encode(tutor.getUsername(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("Could not encode message", e);
        }


        return ResponseEntity.ok("Tutor updated: " + tutor.getId());
    }


    //When returning to profile view from adding skill view
//    private Tutor cleanSkills(Tutor tutor) {
//        Set<Skill> skills = tutor.getSkills();
//
//        Iterator<Skill> it = skills.iterator();
//        while (it.hasNext()) {
//            Skill skill = it.next();
//            if (skill.getName() == null) {
//                it.remove();
//                skillService.deleteSkill(skill);
//            }
//        }
//
//        tutor.setSkills(skills);
//        tutorService.saveTutor(tutor);
//        return tutor;
//    }
}
