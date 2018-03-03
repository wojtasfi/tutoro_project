package com.tutoro.rest;

import com.tutoro.entities.Skill;
import com.tutoro.service.SkillService;
import com.tutoro.service.TutorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by wojci on 4/15/2017.
 */

@RestController
@RequestMapping("home/")
public class HomeRest {

    private static Logger LOGGER = LoggerFactory.getLogger(HomeRest.class);

    @Autowired
    private TutorService tutorService;

    @Autowired
    private SkillService skillService;

    @RequestMapping(method = GET)
    public String home() {

        List<Skill> skills = skillService.findAll().stream().limit(100).collect(Collectors.toList());

        Random random = new Random();

        try {
            Skill skill = skills.get(random.nextInt(skills.size() - 1));
            return skill.getName();
        } catch (Exception e) {
            LOGGER.info("Skills are empty");
        }

        return "joggling";
    }

//    @RequestMapping(value = "/logout", method = RequestMethod.GET)
//    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null) {
//            new SecurityContextLogoutHandler().logout(request, response, auth);
//        }
//        return "redirect:/login?logout";
//    }
//
//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public String login() {
//        return "login";
//    }

}
