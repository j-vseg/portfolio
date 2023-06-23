package org.penguin.restfulApi.repository;

import org.penguin.restfulApi.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJPA extends JpaRepository<User, Long> {
    User getUserById(long id);

    User getUserByEmail(String email);
}

