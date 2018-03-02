package com.tutoro.entities;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by wojci on 4/25/2017.
 */

@Entity
@NoArgsConstructor
@ToString
@Data
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @ElementCollection
    private Set<String> tags = new HashSet<>();

    private Long tutorId;

    @Builder
    public Skill(String name, String description, Long tutorId) {
        this.name = name;
        this.description = description;
        this.tutorId = tutorId;
    }

    public void addTag(String tag) {
        tags.add(tag);
    }

    public void removeTag(String tag) {
        tags.remove(tag);
    }
}
