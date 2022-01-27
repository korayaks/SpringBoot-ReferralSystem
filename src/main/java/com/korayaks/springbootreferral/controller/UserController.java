package com.korayaks.springbootreferral.controller;

import com.korayaks.springbootreferral.dto.UserCreateDto;
import com.korayaks.springbootreferral.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/username/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username){
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @GetMapping("/{referralCode}")
    public ResponseEntity<?> getAllByReferralCode(@PathVariable String referralCode){
        return ResponseEntity.ok(userService.getAllByReferralCode(referralCode));
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserCreateDto userCreateDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userCreateDto));
    }
}
