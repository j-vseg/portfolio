package org.penguin.restfulApi.controller.responses;

import lombok.Builder;
import lombok.Data;
import org.penguin.restfulApi.controller.dto.UserDTO;

@Data
@Builder
public class GetUserResponse {
    private UserDTO user;
}
