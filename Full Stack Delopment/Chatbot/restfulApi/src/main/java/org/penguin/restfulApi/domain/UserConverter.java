package org.penguin.restfulApi.domain;

import org.penguin.restfulApi.controller.dto.UserDTO;
import org.penguin.restfulApi.repository.entity.User;

public class UserConverter {
    public static UserDTO toUserDTO(User entity) {
        if(entity == null) return null;

        return UserDTO.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .role(entity.getRole())
                .build();
    }
}
