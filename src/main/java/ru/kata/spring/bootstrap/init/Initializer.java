package ru.kata.spring.bootstrap.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.bootstrap.model.Role;
import ru.kata.spring.bootstrap.model.User;
import ru.kata.spring.bootstrap.repository.RoleRepository;
import ru.kata.spring.bootstrap.repository.UserRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class Initializer implements CommandLineRunner {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private Set<Role> rolesSet;

    @Autowired
    public Initializer(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        Role ROLE_USER = new Role("ROLE_USER");
        roleRepository.save(ROLE_USER);
        Role ROLE_ADMIN = new Role("ROLE_ADMIN");
        roleRepository.save(ROLE_ADMIN);
        User admin = new User("admin", "admin", 29, "admin@admin.com", passwordEncoder.encode("admin"), null);
        admin.setRoles(new HashSet<>(Arrays.asList(ROLE_ADMIN, ROLE_USER)));
        userRepository.save(admin);
        User user = new User("user", "user", 28, "user@user.com", passwordEncoder.encode("user"), null);
        user.setRoles(new HashSet<>(Collections.singletonList(ROLE_USER)));
        userRepository.save(user);
    }
}
