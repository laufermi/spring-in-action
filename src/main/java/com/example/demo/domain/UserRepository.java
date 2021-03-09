package com.example.demo.domain;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findByName(String name);

    void save(User user);

    List<User> findAll();

    void delete(User user);
}
