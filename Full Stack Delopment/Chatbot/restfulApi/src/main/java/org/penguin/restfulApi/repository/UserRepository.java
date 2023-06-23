package org.penguin.restfulApi.repository;
import org.penguin.restfulApi.domain.UserLogin;
import org.penguin.restfulApi.repository.entity.UserLoginEntity;

import java.util.List;

public interface UserRepository {
    UserLoginEntity get(long id);

    UserLoginEntity get(String name);

    List<UserLoginEntity> get();

    UserLoginEntity create(UserLogin user);

    boolean remove(long id);

    UserLoginEntity update(long id, UserLogin user);
}

