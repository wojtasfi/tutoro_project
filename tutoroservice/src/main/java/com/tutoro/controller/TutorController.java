package com.tutoro.controller;

import com.tutoro.entities.Skill;
import com.tutoro.entities.Tutor;
import com.tutoro.service.SkillService;
import com.tutoro.service.TutorService;
import com.tutoro.web.DuplicateUserError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Set;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by wojci on 4/26/2017.
 */
@Controller
@RequestMapping("/tutor")
public class TutorController {

    @Autowired
    private TutorService tutorService;

    @Autowired
    private SkillService skillService;

    private static Logger LOGGER = LoggerFactory.getLogger(TutorController.class);


    @RequestMapping(value = "/register", method = GET)
    public String register(Model model) {

        Tutor tutor = new Tutor();
        DuplicateUserError error = new DuplicateUserError();
        model.addAttribute("tutor", tutor);
        model.addAttribute("error", error);

        return "registration";
    }

    @RequestMapping(value = "/register", method = POST)
    public String registerNewTutor(@ModelAttribute Tutor tutor, Model model) {

        if (!tutorService.checkIfTutorExists(tutor.getUsername())) {
            tutorService.saveTutor(tutor);
            return "redirect:/tutor/profile/" + tutor.getUsername();
        } else {

            DuplicateUserError error = new DuplicateUserError();
            error.setError(true);
            tutor.setUsername("");

            model.addAttribute("error", error);
            model.addAttribute("tutor", tutor);
            return "registration";
        }


    }

    @RequestMapping(value = "profile/{username}", method = RequestMethod.GET)
    public String showProfile(@PathVariable("username") String username, Model model) {

        if (!model.containsAttribute("tutor")) {

            Tutor tutor = tutorService.findByUsername(username);
            LOGGER.info(tutor.toString());
            cleanSkills(tutor);
            model.addAttribute("tutor", tutor);
        }
        return "profile";
    }

    @RequestMapping(value = "profile/edit/{username}", method = RequestMethod.GET)
    public String showEditProfile(@PathVariable("username") String username, Model model) {

        if (!model.containsAttribute("tutor")) {

            Tutor tutor = tutorService.findByUsername(username);
            LOGGER.info(tutor.toString());
            cleanSkills(tutor);
            model.addAttribute("tutor", tutor);
        }
        return "editProfile";
    }

    @RequestMapping(value = "/profile/edit", method = RequestMethod.POST)
    public String editProfile(@ModelAttribute Tutor tutor, Model model) {
        LOGGER.info(tutor.toString());

        Tutor oldTutor = tutorService.findByUsername(tutor.getUsername());

        tutor.setPassword(oldTutor.getPassword());
        tutor.setProfilePic(oldTutor.getProfilePic());
        tutorService.saveTutor(tutor);
        LOGGER.info(tutor.getUsername());

        String encodedUsername = null;
        try {
            encodedUsername = URLEncoder.encode(tutor.getUsername(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("Could not encode message", e);
        }


        return "redirect:/tutor/profile/" + encodedUsername;
    }


    //When returning to profile view from adding skill view
    private Tutor cleanSkills(Tutor tutor) {
        Set<Skill> skills = tutor.getSkills();

        Iterator<Skill> it = skills.iterator();
        while (it.hasNext()) {
            Skill skill = it.next();
            if (skill.getName() == null) {
                it.remove();
                skillService.deleteSkill(skill);
            }
        }

        tutor.setSkills(skills);
        tutorService.saveTutor(tutor);
        return tutor;
    }
}
