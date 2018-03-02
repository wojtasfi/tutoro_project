package com.tutoro.dto;

import com.tutoro.entities.Skill;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Builder
@Data
public class StudentDto {

    private Long id;
    private String name;
    private String lastName;
    private String username;
    private String email;
    private String skype;
    private String story;

    Set<Skill> learningSkills;
}