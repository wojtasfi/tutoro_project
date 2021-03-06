package com.relationservice.entities.neo4j;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SkillNode {

    @GraphId
    private Long id;
    private String name;
}
