package com.tutoro.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TutorDto {
    private String userName;
    private Long id;
}
