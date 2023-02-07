package ru.kata.spring.bootstrap.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.bootstrap.model.User;
import ru.kata.spring.bootstrap.service.RoleServiceImpl;
import ru.kata.spring.bootstrap.service.UserServiceImpl;

@Controller
public class AdminController {

    private final UserServiceImpl userServiceImpl;
    private final RoleServiceImpl roleServiceImpl;

    public AdminController(UserServiceImpl userServiceImpl,
                           RoleServiceImpl roleServiceImpl) {
        this.userServiceImpl = userServiceImpl;
        this.roleServiceImpl = roleServiceImpl;
    }

    @GetMapping("/admin")
    public String getAdminPage(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("authorizedUser", userDetails);
        model.addAttribute("newUser", new User());
        model.addAttribute("users", userServiceImpl.getAllUsers());
        model.addAttribute("allRoles", roleServiceImpl.getAllRoles());
        return "admin";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute User newUser) {
        userServiceImpl.createUser(newUser);
        return "redirect:/admin";
    }

//    @PostMapping("/update")
    @PutMapping("/update")
    public String updateUser(@ModelAttribute User editUser) {
        userServiceImpl.updateUser(editUser);
        return "redirect:/admin";
    }

//    @PostMapping("/delete/{id}")
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@ModelAttribute("id") long id) {
        User user = userServiceImpl.getUserById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userServiceImpl.deleteUser(user);
        return "redirect:/admin";
    }

}
