package com.korayaks.springbootreferral.dto;

import lombok.Data;

@Data
public class UserReadDto {
    private Long id;
    private String username;

    private String firstName;
    private String lastName;
    private String email;
    private String referralCode;
    private String referredByCode;
}
