package org.penguin.restfulApi.domain;

import lombok.RequiredArgsConstructor;
import org.penguin.restfulApi.controller.dto.UserDTO;

@RequiredArgsConstructor
public class UserAuthorizationRule implements AuthorizationRule {
    private final UserDTO userLoginExpected;
    private final UserDTO userLoginActual;

    public boolean check() {
        if(userLoginExpected == null || userLoginActual == null)
            return false;

        return (userLoginExpected.getId() == userLoginActual.getId());
    }
}
