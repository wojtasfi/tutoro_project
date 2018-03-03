package com.relationservice.service;

import com.relationservice.dao.neo4j.SkillNodeRepository;
import com.relationservice.entities.neo4j.SkillNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillNodeService {

    @Autowired
    private SkillNodeRepository skillNodeRepository;

    public SkillNode findByName(String skillName) {
        return skillNodeRepository.findByName(skillName);
    }
}
