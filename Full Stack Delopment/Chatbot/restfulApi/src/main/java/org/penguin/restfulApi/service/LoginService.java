package org.penguin.restfulApi.service;

import org.penguin.restfulApi.controller.requests.LoginRequest;
import org.penguin.restfulApi.domain.AuthToken;

public interface LoginService {
    AuthToken login(LoginRequest request);
}
