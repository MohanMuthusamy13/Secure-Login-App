package com.example.SecureLoginApp.service;

import com.example.SecureLoginApp.entities.Role;
import com.example.SecureLoginApp.entities.User;
import com.example.SecureLoginApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        validateEmail(email);
        User user = userRepository.findByEmail(email);
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                mapToAuthorities(user.getRoles())
        );
    }

    private Collection<? extends GrantedAuthority> mapToAuthorities(Set<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
    }

    private void validateEmail(String email) {
        Boolean existsByEmail = userRepository.existsByEmail(email);
        if (!existsByEmail) {
            throw new UsernameNotFoundException("Invalid username or email " + email);
        }
    }
}
