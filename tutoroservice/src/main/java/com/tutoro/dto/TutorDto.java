package com.tutoro.dto;

import com.tutoro.entities.Skill;
import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
public class TutorDto {
    private String userName;
    private Long id;
    private Set<Skill> skills = new HashSet<>();
}
