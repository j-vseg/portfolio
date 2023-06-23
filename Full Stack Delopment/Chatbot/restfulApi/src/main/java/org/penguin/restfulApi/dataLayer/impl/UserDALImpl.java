package org.penguin.restfulApi.dataLayer.impl;

import org.penguin.restfulApi.dataLayer.UserDAL;
import org.penguin.restfulApi.repository.entity.User;
import org.penguin.restfulApi.repository.UserJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDALImpl implements UserDAL {

    @Autowired
    UserJPA userJPA;

    @Override
    public User getUserById(long id) {
        return userJPA.getUserById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return userJPA.getUserByEmail(email);
    }

    @Override
    public User saveAndFlush(User user) {
        return userJPA.saveAndFlush(user);
    }

    @Override
    public boolean deleteById(long id) {
        userJPA.deleteById(id);
        return !userJPA.existsById(id);
    }

}

