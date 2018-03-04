package com.relationservice.entities.neo4j;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 * Created by wojciech on 09.07.17.
 */
@RelationshipEntity(type = "LEARNING_FROM")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LearningFromRelationship {

    @GraphId
    private Long id;
    @EndNode
    private TutorNode teacher;
    @StartNode
    private TutorNode student;

    private String skill;
    private Long skillId;

    private String startDate;

    private String endDate;

}
