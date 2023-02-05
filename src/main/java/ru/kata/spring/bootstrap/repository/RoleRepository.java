package ru.kata.spring.bootstrap.repository;


import org.springframework.data.repository.CrudRepository;
import ru.kata.spring.bootstrap.model.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
}
