package ru.kata.spring.bootstrap.service;

import ru.kata.spring.bootstrap.model.User;

import java.util.Optional;

public interface UserService {

    void createUser(User user);

    Iterable<User> getAllUsers();

    void updateUser(User user);

    void deleteUser(User user);

    Optional<User> getUserById(Long id);
}
