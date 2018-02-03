package com.tutoro.controller;

import com.tutoro.entities.Skill;
import com.tutoro.entities.Tutor;
import com.tutoro.service.TutorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by wojci on 4/15/2017.
 */

@Controller
@RequestMapping("/")
public class HomeController {

    private static Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private TutorService tutorService;

    @RequestMapping(method = GET)
    public String home(Model model) {

        List<Tutor> tutors = tutorService.findAll();
        List<Skill> skills = new ArrayList<>();

        for (Tutor tutor : tutors) {
            skills.addAll(tutor.getSkills());
        }

        Random random = new Random();

        try {
            Skill skill = skills.get(random.nextInt(skills.size() - 1));
            model.addAttribute("skill", skill.getName());
        } catch (Exception e) {
            LOGGER.info("Skills are empty");
        }

        return "home";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

}
