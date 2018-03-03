package com.tutoro.service;

import com.tutoro.dao.SkillRepository;
import com.tutoro.dao.TutorRepository;
import com.tutoro.entities.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by wojci on 4/16/2017.
 */

@Service
public class SkillService {

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private SkillRepository skillRepository;

    public Skill saveSkill(Skill skill) {
        skillRepository.save(skill);
        return skill;
    }

    public Skill deleteSkill(Skill skill) {
        skillRepository.delete(skill);
        return skill;
    }

    public Skill getSkillById(Long id) {
        return skillRepository.getSkillById(id);
    }

    public Set<Skill> getSkillByTutorId(Long tutorId) {
        return skillRepository.findByTutorId(tutorId);
    }

    public Skill deleteSkill(Long skillId) {
        Skill skill = skillRepository.getSkillById(skillId);
        skillRepository.delete(skill);
        return skill;
    }

    public Set<Skill> getSkillByTutorUsername(String tutorUsername) {
        Long tutorId = tutorRepository.findByUsername(tutorUsername).getId();
        return skillRepository.findByTutorId(tutorId);
    }

    public List<Skill> findAll() {
        return (List<Skill>) skillRepository.findAll();
    }
}
