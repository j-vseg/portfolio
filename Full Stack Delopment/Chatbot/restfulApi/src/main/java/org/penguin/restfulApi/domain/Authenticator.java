package org.penguin.restfulApi.domain;

import lombok.RequiredArgsConstructor;
import org.penguin.restfulApi.controller.dto.UserDTO;
import org.penguin.restfulApi.repository.UserJPA;
import org.penguin.restfulApi.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Authenticator {

    @Autowired
    UserJPA userRepository;

    public UserDTO authenticate(String authStr) {
        if(authStr == null || authStr.indexOf(' ') == -1) return null;

        String authUsername = authStr.substring(0, authStr.indexOf(' '));
        String authPassword = authStr.substring(authStr.indexOf(' ') + 1);
        User user = userRepository.getUserByEmail(authUsername);
        StringBuilder passwordHash = new StringBuilder();
        /*
        try {
            byte[] hashBytes = MessageDigest.getInstance("MD5").digest(user.getPassword().getBytes());

            for(byte b : hashBytes) {
                passwordHash.append(String.format("%02x", b));
            }
        }
        catch (Exception e) { }
        */
        //disabled password hashing
        passwordHash.append(user.getPassword());

        if(authPassword.equals(passwordHash.toString())) return UserConverter.toUserDTO(user);

        return null;
    }
}
