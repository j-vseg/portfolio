package org.penguin.restfulApi.repository.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLoginEntity {
    private long id;
    private String username;
    private String password;
}
