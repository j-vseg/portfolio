package org.penguin.restfulApi.service.impl;

import org.penguin.restfulApi.controller.requests.LoginRequest;
import org.penguin.restfulApi.dataLayer.UserDAL;
import org.penguin.restfulApi.domain.AuthToken;
import org.penguin.restfulApi.domain.exception.InvalidCredentialsException;
import org.penguin.restfulApi.repository.entity.User;
import org.penguin.restfulApi.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserDAL userDAL;

    @Override
    public AuthToken login(LoginRequest request) {
        User user = userDAL.getUserByEmail(request.getEmail());

        if(user == null || !user.getPassword().equals(request.getPassword())) throw new InvalidCredentialsException();

        return new AuthToken(user);
    }
}
