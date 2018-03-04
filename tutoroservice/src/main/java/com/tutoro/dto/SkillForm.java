package com.tutoro.dto;

import lombok.Data;
import lombok.ToString;

/**
 * Created by wojci on 4/25/2017.
 */
@Data
@ToString
public class SkillForm {

    private Long tutorId;
    private String name;
    private String tag;
    private String description;
    String tags;


}
