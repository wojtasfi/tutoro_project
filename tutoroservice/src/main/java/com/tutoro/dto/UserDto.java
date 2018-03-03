package com.tutoro.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class UserDto {
    @NotNull
    @Min(5)
    private String username;

    @NotNull
    @Min(5)
    private String password;

    @Email
    @NotNull
    private String email;
}
