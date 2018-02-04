package com.relationservice.service;


import com.relationservice.dao.db.LearnRelationRepository;
import com.relationservice.entities.db.LearnRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wojciech on 28.06.17.
 */
@Service
public class LearnRelationService {

    @Autowired
    private LearnRelationRepository learnRelationRepository;


    public void saveRelation(LearnRelation learnRelation) {
        learnRelationRepository.save(learnRelation);

    }


}
