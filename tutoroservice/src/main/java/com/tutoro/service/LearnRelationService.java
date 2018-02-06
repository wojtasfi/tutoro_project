package com.tutoro.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tutoro.dao.LearnRelationRepository;
import com.tutoro.dao.SkillRepository;
import com.tutoro.dao.TutorRepository;
import com.tutoro.dto.LearnRelationRawDataDTO;
import com.tutoro.entities.LearnRelation;
import com.tutoro.entities.Skill;
import com.tutoro.entities.Tutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by wojci on 5/23/2017.
 */
@Service
public class LearnRelationService {

    private static Logger LOGGER = LoggerFactory.getLogger(SearchService.class);

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private LearnRelationRepository learnRelationRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private final ObjectMapper mapper = new ObjectMapper();

    public LearnRelation saveLearnRelation(LearnRelation learnRelation) {
        LearnRelation newLearnRelation = learnRelationRepository.save(learnRelation);
        LearnRelationRawDataDTO dto = LearnRelationRawDataDTO.createFromLearnRelation(newLearnRelation);
        try {
            sendMessage(dto);
        } catch (JsonProcessingException e) {
            LOGGER.error("Unable to parse relation: " + newLearnRelation.toString(), e);
        }
        return newLearnRelation;
    }

    private void sendMessage(final LearnRelationRawDataDTO learnRelation) throws JsonProcessingException {
        LOGGER.info("Sending message to create relation: " + learnRelation.getSkill());
        String json = mapper.writeValueAsString(learnRelation);
        kafkaTemplate.send("learnRelationAdded", json);

    }

    public Boolean learnRelationExists(Skill skill, Tutor teacher, Tutor student) {
        return learnRelationRepository.findByTeacherAndStudentAndSkill(teacher, student, skill) != null;

    }
}
