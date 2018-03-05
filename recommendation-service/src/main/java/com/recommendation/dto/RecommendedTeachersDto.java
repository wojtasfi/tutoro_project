package com.recommendation.dto;

import lombok.Data;

import java.util.List;

@Data
public class RecommendedTeachersDto {
    String teacherUsername;
    List<String> skillNames;
}
