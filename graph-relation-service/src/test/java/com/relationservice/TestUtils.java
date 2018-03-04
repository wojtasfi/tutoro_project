package com.relationservice;


import com.relationservice.dto.LearnRelationRawDataDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TestUtils {


    public LearnRelationRawDataDTO createLearnRelationRawData(String skillName, String studentUsername, String teacherUsername) {
        LearnRelationRawDataDTO rawData = LearnRelationRawDataDTO.builder()
                .skill(skillName)
                .studentUsername(studentUsername)
                .teacherUsername(teacherUsername)
                .startDate(LocalDate.now())
                .build();

        return rawData;

    }

}
