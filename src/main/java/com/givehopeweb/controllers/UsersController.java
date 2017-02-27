package com.givehopeweb.controllers;

import com.givehopeweb.models.Charity;
import com.givehopeweb.models.Donation;
import com.givehopeweb.models.User;
import com.givehopeweb.models.UserRole;
import com.givehopeweb.repositories.Charities;
import com.givehopeweb.repositories.Donations;
import com.givehopeweb.repositories.Roles;
import com.givehopeweb.repositories.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by David on 2/22/17.
 */
@Controller
public class UsersController {

    private Users users;
    private PasswordEncoder encoder;
    private Roles roles;
    private Donations donationsDao;
    private Charities charitiesDao;

    @Autowired
    public UsersController (Users users, PasswordEncoder encoder, Roles roles, Donations
            donationsDao, Charities charitiesDao) {
        this.users = users;
        this.encoder = encoder;
        this.roles = roles;
        this.donationsDao = donationsDao;
        this.charitiesDao = charitiesDao;
    }

    @GetMapping ("/register")
    public String showRegistrationPage (Model model) {

        model.addAttribute("user", new User() );
        return  "/users/login-register";
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
            return "/users/login-register";
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

    @GetMapping ("/profile")
    public String showUser (Model model) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);

        List<Donation> donations = donationsDao.findByUserId(user.getId());

        Donation totalDonation = new Donation();

        for (Donation donation : donations) {
            if (totalDonation.getAmount() == null) {
                totalDonation.setAmount(donation.getAmount());
            }

            totalDonation.setAmount(totalDonation.getAmount().add(donation.getAmount()));
        }

        model.addAttribute("totalDonation", totalDonation);

        List<Charity> favoriteCharities = charitiesDao.findUserFavorites(user.getId());
        model.addAttribute("favorites", favoriteCharities);

        return "/users/profile";
    }

    @GetMapping ("/login")
    public String showLoginForm () {

        return "/users/login-register";
    }

}
