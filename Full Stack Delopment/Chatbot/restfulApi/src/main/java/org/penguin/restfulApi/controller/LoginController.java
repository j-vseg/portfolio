package org.penguin.restfulApi.controller;

import org.penguin.restfulApi.controller.requests.LoginRequest;
import org.penguin.restfulApi.controller.responses.LoginResponse;
import org.penguin.restfulApi.domain.AuthToken;
import org.penguin.restfulApi.domain.AuthTokenConverter;
import org.penguin.restfulApi.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("login")
public class LoginController {
    @Autowired
    private AuthTokenConverter authTokenConverter;
    @Autowired
    private LoginService loginService;

    @PostMapping
    @CrossOrigin
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        AuthToken authToken = loginService.login(request);

        return ResponseEntity.ok(LoginResponse.builder()
                .user(authToken.getSubject())
                .authToken(authTokenConverter.toJson(authToken))
                .build());
    }
}
