package com.tutoro.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tutoro.dao.LearnRelationRawDataRepository;
import com.tutoro.dao.LearnRelationRepository;
import com.tutoro.dao.SkillRepository;
import com.tutoro.dao.TutorRepository;
import com.tutoro.dto.LearnRelationRawDataDTO;
import com.tutoro.entities.LearnRelation;
import com.tutoro.entities.LearnRelationRawData;
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
    private LearnRelationRawDataRepository learnRelationRawDataRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;


    public LearnRelation saveLearnRelation(LearnRelation learnRelation) {
        LearnRelation newLearnRelation = learnRelationRepository.save(learnRelation);
        Tutor student = tutorRepository.findOne(learnRelation.getStudentId());
        Tutor teacher = tutorRepository.findOne(learnRelation.getTeacherId());
        Skill skill = skillRepository.findOne(learnRelation.getSkillId());

        LearnRelationRawData rawData = LearnRelationRawData
                .createFromLearnRelation(newLearnRelation, student, teacher, skill);

        learnRelationRawDataRepository.save(rawData);

        LearnRelationRawDataDTO dto = LearnRelationRawDataDTO.createFromRawData(rawData);

        try {
            sendMessage(dto);
        } catch (JsonProcessingException e) {
            LOGGER.error("Unable to parse relation: " + newLearnRelation.toString(), e);
        }
        return newLearnRelation;
    }

    private void sendMessage(final LearnRelationRawDataDTO learnRelation) throws JsonProcessingException {
        LOGGER.info("Sending message to create relation: " + learnRelation.getSkill());
        String json = objectMapper.writeValueAsString(learnRelation);
        kafkaTemplate.send("learnRelationAdded", json);

    }

    public Boolean learnRelationExists(Skill skill, Tutor teacher, Tutor student) {
        return learnRelationRepository.findByTeacherIdAndStudentIdAndSkillId(teacher.getId(), student.getId(), skill.getId()) != null;

    }

    public LearnRelation getById(Long relationId) {
        return learnRelationRepository.findOne(relationId);
    }
}
