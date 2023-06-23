package org.penguin.restfulApi.repository.impl;

import lombok.RequiredArgsConstructor;
import org.penguin.restfulApi.domain.UserLogin;
import org.penguin.restfulApi.repository.UserRepository;
import org.penguin.restfulApi.repository.entity.UserLoginEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
//@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    //TODO: remove when connected
    public UserRepositoryImpl() {
        testRepo = new HashMap<>();
        nextId = 0;

        create(UserLogin.builder().username("janne@test.nl").password("password").build());
        create(UserLogin.builder().username("test").password("password").build());
    }
    //TODO: connect to db
    private Map<Long, UserLoginEntity> testRepo;
    private long nextId;

    public UserLoginEntity get(long id) {
        return testRepo.get(id);
    }

    public UserLoginEntity get(String name) {
        UserLoginEntity entity = null;
        Object[] array = testRepo.values().stream().map(user -> {
            if (user.getUsername().equals(name)) {
                return user;
            }

            return null;
        }).filter((obj) -> Objects.nonNull(obj)).toArray();

        if (array.length > 0) entity = (UserLoginEntity) array[0];

        return entity;
    }

    public List<UserLoginEntity> get() {
        return List.copyOf(testRepo.values());
    }

    public UserLoginEntity create(UserLogin user) {
        UserLoginEntity entity = UserLoginEntity.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .id(nextId)
                .build();
        testRepo.put(nextId++, entity);
        return entity;
    }

    public boolean remove(long id) {
        return testRepo.remove(id) != null;
    }

    @Override
    public UserLoginEntity update(long id, UserLogin user) {
        if (!testRepo.containsKey(id)) return null;

        UserLoginEntity entity = UserLoginEntity.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .id(id)
                .build();
        testRepo.put(id, entity);
        return entity;

    }
}
