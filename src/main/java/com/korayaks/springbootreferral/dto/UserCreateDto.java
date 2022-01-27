package com.korayaks.springbootreferral.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UserCreateDto {
    @NotNull
    @NotEmpty
    private String user;
    @NotNull @NotEmpty
    private String referredByCode;
    @NotNull @NotEmpty
    private String firstName;

    private String lastName;
    private String email;
}
