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

        if (!SecurityContextHolder.getContext().getAuthentication().getPrincipal()
                .equals("anonymousUser")) {

            User user = (User) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();

            model.addAttribute("user", user);
        }


        return "/home-screen";
    }

    @GetMapping ("/donate")
    public String showDonationPage(Model model) {

        if (!SecurityContextHolder.getContext().getAuthentication().getPrincipal()
                .equals("anonymousUser")) {

            User user = (User) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();

            model.addAttribute("user", user);
        }

        return "/charities/donation-form";
    }

    @GetMapping ("/categories")
    public String showCategoriesPage() {
        return "/charities/categories";
    }

}
