package org.penguin.restfulApi.service;

import org.penguin.restfulApi.controller.dto.UserDTO;
import org.penguin.restfulApi.repository.entity.User;

public interface UserService {
    UserDTO getUserById(long id);

    UserDTO getUserByEmail(String email);

    UserDTO saveAndFlush(User user);

    boolean deleteByUserId(long id);

}
