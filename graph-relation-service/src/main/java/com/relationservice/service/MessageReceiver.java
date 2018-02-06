package com.relationservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.relationservice.entities.db.LearnRelationRawData;
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

    private final ObjectMapper mapper = new ObjectMapper();
    private static Logger LOGGER = LoggerFactory.getLogger(MessageReceiver.class);

    @Autowired
    private LearnRelationService learnRelationService;

    @Autowired
    private TutorNodeService tutorNodeService;

    @KafkaListener(topics = "learnRelationAdded")
    public void receiveMessage(String learnRelationJson) {
        LOGGER.info("Received <" + learnRelationJson + ">");

        LearnRelationRawData learningRelation = null;
        try {
            learningRelation = mapper.readValue(learnRelationJson, LearnRelationRawData.class);
        } catch (IOException e) {
            LOGGER.error("Could not read json value");
        }
        handleNewRelation(learningRelation);

    }

    @Transactional
    private void handleNewRelation(LearnRelationRawData learnRelationRawData) {
        LOGGER.info("Saving to graph");
        tutorNodeService.processLearnRelationRawData(learnRelationRawData);
        learnRelationService.saveRelation(learnRelationRawData);

    }

}
