package com;


import com.tutoro.dao.*;
import com.tutoro.entities.LearnRelation;
import com.tutoro.entities.Skill;
import com.tutoro.entities.Tutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TestUtils {

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private LearnRelationRepository learnRelationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailVerificationTokenRepository tokenRepository;

    public Tutor addTutor(String name, String lastName, String username) {
        Tutor tutor = Tutor.builder()
                .name(name)
                .lastName(lastName)
                .username(username)
                .email(username + "@test.com")
                .password("test")
                .skype(username + "Skype")
                .build();

        return tutorRepository.save(tutor);
    }

    public Skill findSkillByName(String name) {
        return skillRepository.findByName(name);
    }

    public void prepareRandomSkills(int numebrOfSkills, Long tutorId) {
        for (int i = 0; i <= numebrOfSkills; i++) {
            skillRepository.save(Skill.builder()
                    .name("Test" + i)
                    .description("Description of " + i)
                    .tutorId(tutorId)
                    .build());
        }

    }

    public Skill prepareSkill(String name, Long tutorId) {
        return skillRepository.save(Skill.builder()
                .name(name)
                .description(name)
                .tutorId(tutorId)
                .build());
    }

    public LearnRelation createLearnRealtion(Long studentId, Long teacherId, Long skillId) {
        LearnRelation learnRelation = LearnRelation.builder()
                .skillId(skillId)
                .teacherId(teacherId)
                .studentId(studentId)
                .startDate(LocalDate.now())
                .build();
        return learnRelationRepository.save(learnRelation);

    }
}
