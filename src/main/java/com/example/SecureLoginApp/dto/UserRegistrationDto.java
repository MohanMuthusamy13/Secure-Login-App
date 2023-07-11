package com.example.SecureLoginApp.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserRegistrationDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
