package org.penguin.restfulApi.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLogin {
    private String username;
    private String password;
}
