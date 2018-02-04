package com.relationservice.service;


import com.relationservice.dao.db.LearnRelationRepository;
import com.relationservice.entities.db.LearnRelation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wojciech on 28.06.17.
 */
@Service
public class LearnRelationService {
    private static Logger LOGGER = LoggerFactory.getLogger(LearnRelationService.class);

    @Autowired
    private LearnRelationRepository learnRelationRepository;


    public void saveRelation(LearnRelation learnRelation) {
        LOGGER.info("Saving relation for {}", learnRelation.getTeacherUsername());
        learnRelationRepository.save(learnRelation);

    }


}
