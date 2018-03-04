package com.relationservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.relationservice.dto.LearnRelationRawDataDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

/**
 * Created by wojciech on 27.06.17.
 */

@Component
public class MessageReceiver {

    private static Logger LOGGER = LoggerFactory.getLogger(MessageReceiver.class);

    @Autowired
    private TutorNodeService tutorNodeService;

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "learnRelationAdded", group = "relation-service")
    public void receiveMessage(String learnRelationJson) {
        LOGGER.info("Received <" + learnRelationJson + ">");

        LearnRelationRawDataDTO learningRelation = new LearnRelationRawDataDTO();
        try {
            learningRelation = objectMapper.readValue(learnRelationJson, LearnRelationRawDataDTO.class);
        } catch (IOException e) {
            LOGGER.error("Could not read json value", e);
        }
        handleNewRelation(learningRelation);

    }

    @Transactional
    private void handleNewRelation(LearnRelationRawDataDTO dto) {
        LOGGER.info("Saving to graph");

        tutorNodeService.processLearnRelationRawData(dto);

    }

}
