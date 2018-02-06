package com.relationservice.entities.neo4j;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import java.time.LocalDate;

/**
 * Created by wojciech on 09.07.17.
 */
@RelationshipEntity(type = "TEACHING")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeachingRelationship {

    @GraphId
    private Long id;

    @StartNode
    private TutorNode teacher;

    @EndNode
    private TutorNode student;

    private String skill;
    private Long skillId;

    private LocalDate startDate;
    private LocalDate endDate;
}
