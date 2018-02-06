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
@RelationshipEntity(type = "IS_TEACHING")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IsTeachingRelationship {

    @GraphId
    private Long id;
    @EndNode
    private SkillNode skill;
    @StartNode
    private TutorNode teacher;

    private LocalDate startDate;
    private LocalDate endDate;

}
