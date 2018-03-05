package com.recommendation.results;

import lombok.Data;
import org.springframework.data.neo4j.annotation.QueryResult;

@QueryResult
@Data
public class RecommendedTeacherResult {
    String teacherUsername;
    String skillName;
}
