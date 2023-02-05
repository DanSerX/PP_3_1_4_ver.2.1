package ru.kata.spring.bootstrap.repository;

import org.springframework.data.repository.CrudRepository;
import ru.kata.spring.bootstrap.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
}
