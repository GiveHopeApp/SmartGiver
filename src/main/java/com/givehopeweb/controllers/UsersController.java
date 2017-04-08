package com.givehopeweb.controllers;

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
import java.math.BigDecimal;
import java.util.List;

/**
 * <p>The <code>UsersController</code> class handles navigation to user related pages:
 * registration, login, profile.</p>
 *
 * @author David Ryan Alviola
 * @since March 2017
 */
@Controller
public class UsersController {

    //Injected classes
    private Users users;
    private PasswordEncoder encoder;
    private Roles roles;
    private Donations donationsDao;

    /**
     * <p>Constructor for the <code>UsersController</code> class.</p>
     *
     * @param users interface with methods that query the database
     * @param encoder used to encrypt the user's password
     * @param roles interface used to assign roles
     * @param donationsDao interface with methods to query the database
     */
    @Autowired
    public UsersController (Users users, PasswordEncoder encoder, Roles roles, Donations
            donationsDao) {
        this.users = users;
        this.encoder = encoder;
        this.roles = roles;
        this.donationsDao = donationsDao;
    }

    /**
     * <p>The <code>showRegistrationPage</code> method displays the login/registration view.</p>
     *
     * @param model holds various objects for reference
     * @return login/registration page
     */
    @GetMapping ("/register")
    public String showRegistrationPage (Model model) {

        if (!SecurityContextHolder.getContext().getAuthentication().getPrincipal()
                .equals("anonymousUser")) {

            User user = (User) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();

            model.addAttribute("user", user);
        } else {

            model.addAttribute("user", new User() );
        }

        return  "/users/login-register";
    }

    /**
     * <p>The <code>registerUser</code> method handles the POST request sent when registering a
     * new user. Form validation occurs in the back-end and redirects the user back to the
     * registration page with appropriate error messages if invalid data is submitted.</p>
     *
     * @param user object used to store user information
     * @param validation object used for form validation
     * @param model holds various objects for reference
     * @param passwordConfirmation String value used to verify entered passwords match
     * @return redirects to the login/registration page if successful
     */
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

        return "redirect:/register";
    }

    /**
     * <p>The <code>showUser</code> method displays the user profile view. The user's total
     * donation amount is calculated and displayed. A list of all the user's donations are also
     * displayed.</p>
     *
     * @param model holds user object to reference user properties
     * @return user profile page
     */
    @GetMapping ("/profile")
    public String showUser (Model model) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);

        List<Donation> donations = donationsDao.findByUserId(user.getId());
        model.addAttribute("donations", donations);

        BigDecimal totalDonation = new BigDecimal(0).setScale(2);

        //Iterates through and sums up the user's donations
        for (Donation donation : donations) {

            totalDonation = totalDonation.add(donation.getAmount().setScale(2));
        }

        model.addAttribute("totalDonation", totalDonation);

        return "/users/profile";
    }

    /**
     * <p>The <code>saveProfilePic</code> method saves the URL generated by the FileStack API to
     * the user object and updates the database entry. Users must logout and login before the
     * picture updates.</p>
     *
     * @param url String value representing the url where the profile picture is stored
     * @return redirects to the user profile
     */
    @PostMapping ("/profile")
    public String saveProfilePic (@RequestParam (name = "url") String url) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User saveUser = users.findOne(user.getId());

        saveUser.setProfilePicture(url);

        users.save(saveUser);

        return "redirect:/profile";
    }

    /**
     * <p>The <code>showLoginForm</code> method displays the login/registration page. This method
     * is identical to the <code>showRegistrationForm</code> method, but must be present for
     * Spring Security to handle login/logout correctly.</p>
     *
     * @param model holds various objects for reference
     * @return displays the login/registration page
     */
    @GetMapping ("/login")
    public String showLoginForm (Model model) {

        if (!SecurityContextHolder.getContext().getAuthentication().getPrincipal()
                .equals("anonymousUser")) {

            User user = (User) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();

            model.addAttribute("user", user);
        } else {

            model.addAttribute("user", new User() );
        }

        return "/users/login-register";
    }

}
