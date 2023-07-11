package com.example.SecureLoginApp.service;

import com.example.SecureLoginApp.dto.UserRegistrationDto;
import com.example.SecureLoginApp.dto.UserResponseDto;
import com.example.SecureLoginApp.entities.Role;
import com.example.SecureLoginApp.entities.User;
import com.example.SecureLoginApp.repository.RoleRepository;
import com.example.SecureLoginApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto saveUser(UserRegistrationDto userRegistrationDto) {
        User user = getUserFromUserRegistrationDto(userRegistrationDto);
        Role roleAdmin = roleRepository.findByName("ROLE_ADMIN");
        if (Objects.isNull(roleAdmin)) {
            addAdminRole(user);
        }
        System.out.println(user);
        User savedUser = userRepository.save(user);
        return UserResponseDto.fromUser(savedUser);
    }

    private void addAdminRole(User user) {
        Role role = new Role()
                .setName("ROLE_ADMIN");
        roleRepository.save(role);
        Role roleAdmin = roleRepository.findByName("ROLE_ADMIN");
        user.setRoles(Collections.singleton(roleAdmin));
    }

    private User getUserFromUserRegistrationDto(UserRegistrationDto userRegistrationDto) {
        return new User()
                .setFirstName(userRegistrationDto.getFirstName())
                .setLastName(userRegistrationDto.getLastName())
                .setEmail(userRegistrationDto.getEmail())
                .setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
    }
}
