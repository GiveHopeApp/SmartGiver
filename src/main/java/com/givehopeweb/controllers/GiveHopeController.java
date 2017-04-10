package com.givehopeweb.controllers;

import com.givehopeweb.models.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <p>The <code>GiveHopeController</code> class handles navigation to the main and
 * categories pages.</p>
 *
 * @author David Ryan Alviola
 * @since March 2017
 */
@Controller
public class GiveHopeController {

    /**
     * <p>The <code>showLandingPage</code> method shows the main page.</p>
     *
     * @param model holds various objects for reference
     * @return the landing page view
     */
    @GetMapping ("/")
    public String showLandingPage (Model model) {

        //Adds logged in user object to model for navbar personalization
        if (!SecurityContextHolder.getContext().getAuthentication().getPrincipal()
                .equals("anonymousUser")) {

            User user = (User) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();

            model.addAttribute("user", user);
        }


        return "/home-screen";
    }

    /**
     * <p>The <code>showCategoriesPage</code> method shows the categories view.</p>
     *
     * @param model holds various objects for reference
     * @return categories view
     */
    @GetMapping ("/categories")
    public String showCategoriesPage(Model model) {

        //Adds logged in user object to model for navbar personalization
        if (!SecurityContextHolder.getContext().getAuthentication().getPrincipal()
                .equals("anonymousUser")) {

            User user = (User) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();

            model.addAttribute("user", user);
        }

        return "/charities/categories";
    }

}
