package com.relationservice.entities.neo4j;

import lombok.*;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * Created by wojciech on 28.06.17.
 */
@NodeEntity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TutorNode {

    @GraphId
    private Long id;
    private String username;
    private String name;
    private String lastName;
    private String email;
    private String skype;
}
