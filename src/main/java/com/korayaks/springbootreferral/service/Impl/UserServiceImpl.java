package com.korayaks.springbootreferral.service.Impl;

import com.korayaks.springbootreferral.dto.UserCreateDto;
import com.korayaks.springbootreferral.dto.UserReadDto;
import com.korayaks.springbootreferral.model.User;
import com.korayaks.springbootreferral.repo.UserRepository;
import com.korayaks.springbootreferral.service.UserService;
import com.korayaks.springbootreferral.util.RandomStringGenerator;
import lombok.AllArgsConstructor;


import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Value("${defaultRefCount}")
    private int defaultRefCount;
    private final RandomStringGenerator randomStringGenerator;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserReadDto getUserByUsername(String username) {
        return modelMapper.map(userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("user not found")), UserReadDto.class);
    }

    @Override
    public List<UserReadDto> getAllByReferralCode(String referralCode) {
        if (userRepository.existsUserByReferralCode(referralCode)) {
            throw new RuntimeException("user not found with given code");
        }
        return modelMapper.map(userRepository.findAllByReferredByCode(referralCode), new TypeToken<List<UserReadDto>>() {
        }.getType());
    }

    @Override
    public UserReadDto createUser(UserCreateDto userCreateDto) {
        if (userCreateDto.getReferredByCode() == null || userCreateDto.getReferredByCode().isEmpty()) {
            throw new RuntimeException("referral code is not valid");
        }
        int referredUserCount = getAllByReferralCode(userCreateDto.getReferredByCode()).size();
        final var user = modelMapper.map(userCreateDto, User.class);
        if (referredUserCount < defaultRefCount) {
            user.setReferralCode(generateCode());
        } else {
            throw new RuntimeException("max person count has been reached");
        }
        userRepository.save(user);
        return modelMapper.map(user, UserReadDto.class);
    }

    private String generateCode() {
        String generated = "";
        do {
            generated = randomStringGenerator.generate();
        } while (userRepository.existsUserByReferralCode(generated));
        return "";
    }
}
