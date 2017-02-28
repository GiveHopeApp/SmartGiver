package com.givehopeweb.controllers;

import com.givehopeweb.models.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by David on 2/22/17.
 */
@Controller
public class GiveHopeController {

    @GetMapping ("/")
    public String showLandingPage (Model model) {

//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        model.addAttribute("user", user);

        return "/home-screen";
    }

    @GetMapping ("/donate")
    public String showDonationPage() {
        return "/charities/donation-form";
    }

}
