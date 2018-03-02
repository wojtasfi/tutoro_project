package com.tutoro;

import com.TestUtils;
import com.tutoro.entities.Skill;
import com.tutoro.entities.Tutor;
import com.tutoro.service.TutorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TutorTests {

    @Autowired
    private TestUtils testUtils;

    @Autowired
    private TutorService tutorService;


    @Test
    public void shouldAddSkillToTutor() {
        //given
        String skillName = "Cooking";
        String description = "Cooking dinner";

        Tutor tutor = testUtils.addTutor("Wojtek", "Figas", "wfigas");
        Skill skill = new Skill();

        skill.setName(skillName);
        skill.setDescription(description);
        //when
        tutorService.addSkill(skill, tutor);

        //then

        Skill addedSkill = testUtils.findSkillByName(skillName);
        assertNotNull(addedSkill);

        Tutor addedTutor = tutorService.findByUsername(tutor.getUsername());

        assertNotNull(addedTutor);

        assertEquals(skillName, addedSkill.getName());
        assertEquals(description, addedSkill.getDescription());

    }
}

