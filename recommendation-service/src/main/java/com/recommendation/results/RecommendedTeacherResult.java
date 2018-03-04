package com.recommendation.results;

import lombok.Data;
import org.springframework.data.neo4j.annotation.QueryResult;

@QueryResult
@Data
public class RecommendedTeacherResult {
    Long teacherId;
    String skillName;
    Long skillId;
}
