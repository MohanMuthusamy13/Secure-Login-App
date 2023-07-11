package com.example.SecureLoginApp.service;

import com.example.SecureLoginApp.dto.UserRegistrationDto;
import com.example.SecureLoginApp.dto.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
public interface UserService {
    UserResponseDto saveUser(UserRegistrationDto userRegistrationDto);
}
