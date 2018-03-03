package com.relationservice;


import com.relationservice.dao.db.LearnRelationRawDataRepository;
import com.relationservice.entities.db.LearnRelationRawData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TestUtils {

    @Autowired
    private LearnRelationRawDataRepository learnRelationRawDataRepository;

    public LearnRelationRawData createLearnRelationRawData(String skillName, String studentUsername, String teacherUsername) {
        LearnRelationRawData rawData = LearnRelationRawData.builder()
                .skill(skillName)
                .studentUsername(studentUsername)
                .teacherUsername(teacherUsername)
                .startDate(LocalDate.now())
                .build();

        return learnRelationRawDataRepository.save(rawData);

    }

}
