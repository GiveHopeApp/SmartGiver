package com.givehopeweb.controllers;

import com.givehopeweb.models.Charity;
import com.givehopeweb.models.Donation;
import com.givehopeweb.models.User;
import com.givehopeweb.repositories.Charities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by David on 2/26/17.
 */
@Controller
public class CharitiesController {

    private Charities charitiesDao;

    @Autowired
    public CharitiesController (Charities charitiesDao) {
        this.charitiesDao = charitiesDao;
    }

    @GetMapping ("/charities/{id}")
    public String showCharityProfile (@PathVariable int id, Model model) {

        if (!SecurityContextHolder.getContext().getAuthentication().getPrincipal()
                .equals("anonymousUser")) {

            User user = (User) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();

            model.addAttribute("user", user);
        }

        Charity charity = charitiesDao.findOne(id);

        model.addAttribute("charity", charity);
        model.addAttribute("donation", new Donation());

        return "charities/profile";
    }

    @GetMapping ("/thank-you")
    public String showThankYouPage () {
        return "charities/thank-you";
    }
}
