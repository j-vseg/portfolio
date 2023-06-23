package org.penguin.restfulApi.service.impl;

import org.penguin.restfulApi.controller.dto.UserDTO;
import org.penguin.restfulApi.dataLayer.UserDAL;
import org.penguin.restfulApi.domain.UserConverter;
import org.penguin.restfulApi.repository.entity.User;
import org.penguin.restfulApi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDAL userDAL;

    @Override
    public UserDTO getUserById(long id) {
        return UserConverter.toUserDTO(userDAL.getUserById(id));
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        return UserConverter.toUserDTO(userDAL.getUserByEmail(email));
    }

    @Override
    public UserDTO saveAndFlush(User user) {
        return UserConverter.toUserDTO(userDAL.saveAndFlush(user));
    }
    @Override
    public boolean deleteByUserId(long id) {
        return userDAL.deleteById(id);
    }
}
