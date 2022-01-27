package com.korayaks.springbootreferral.service;

import com.korayaks.springbootreferral.dto.UserCreateDto;
import com.korayaks.springbootreferral.dto.UserReadDto;

import java.util.List;


public interface UserService {
    UserReadDto getUserByUsername(String username);
    List<UserReadDto> getAllByReferralCode(String referralCode);
    UserReadDto createUser(UserCreateDto userCreateDto);
}
