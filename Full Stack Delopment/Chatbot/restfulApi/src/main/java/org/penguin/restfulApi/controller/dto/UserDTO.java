package org.penguin.restfulApi.controller.dto;

import lombok.Builder;
import lombok.Data;
import org.penguin.restfulApi.domain.UserRole;

@Data
@Builder
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private UserRole role;
}
