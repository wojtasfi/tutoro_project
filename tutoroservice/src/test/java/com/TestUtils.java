package com;


import com.tutoro.dao.*;
import com.tutoro.entities.Skill;
import com.tutoro.entities.Tutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
