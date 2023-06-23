package org.penguin.restfulApi.dataLayer;

import org.penguin.restfulApi.repository.entity.User;

public interface UserDAL {
    User getUserById(long id);

    User getUserByEmail(String email);

    User saveAndFlush(User user);

    boolean deleteById(long id);

}
