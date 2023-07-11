package com.example.SecureLoginApp.dto;

import com.example.SecureLoginApp.entities.User;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserResponseDto {
    private long id;
    private String firstName;
    private String lastName;
    private String email;

    public static UserResponseDto fromUser(User user) {
        return new UserResponseDto()
                .setId(user.getId())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setEmail(user.getEmail());
    }
}
