package com.givehopeweb.controllers;

import com.givehopeweb.models.User;
import com.givehopeweb.models.UserRole;
import com.givehopeweb.repositories.Roles;
import com.givehopeweb.repositories.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * Created by David on 2/22/17.
 */
@Controller
public class UsersController {

    private Users users;
    private PasswordEncoder encoder;
    private Roles roles;

    @Autowired
    public UsersController (Users users, PasswordEncoder encoder, Roles roles) {
        this.users = users;
        this.encoder = encoder;
        this.roles = roles;
    }

    @GetMapping ("/register")
    public String showRegistrationPage (Model model) {

        model.addAttribute("user", new User() );
        return  "/users/registration";
    }

    @PostMapping("/register")
    public String registerUser (
            @Valid User user,
            Errors validation,
            Model model,
            @RequestParam(name = "passwordConfirm") String passwordConfirmation) {

        if (!passwordConfirmation.equals(user.getPassword())) {
            validation.rejectValue(
                    "password",
                    "user.password",
                    "Passwords do not match"
            );
        }

        if (validation.hasErrors()) {
            model.addAttribute("errors", validation);
            model.addAttribute("user", user);
            return "/users/registration";
        }

        String hash = encoder.encode(user.getPassword());
        user.setPassword(hash);

        User newUser = users.save(user);

        UserRole userRole = new UserRole();
        userRole.setRole("ROLE_USER");
        userRole.setUserId(newUser.getId());

        roles.save(userRole);

        return "redirect:/home-screen";
    }

    @GetMapping ("profile/{id}")
    public String showUser (@PathVariable Integer id, Model model) {

        User user = users.findOne(id);
        model.addAttribute("user", user);

        return "/users/profile";
    }
}
