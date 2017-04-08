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
 * <p>The <code>CharitiesController</code> class handles a majority of the navigation to
 * charities views.</p>
 *
 * @author David Ryan Alviola
 * @since March 2017
 */
@Controller
public class CharitiesController {

    private Charities charitiesDao;

    /**
     * <p>Constructor for <code>CharitiesController</code>. A <code>charitiesDao</code> object
     * is injected to provide methods for querying the charities table.</p>
     *
     * @param charitiesDao a <code>Charities</code> interface with methods to query the database
     */
    @Autowired
    public CharitiesController (Charities charitiesDao) {
        this.charitiesDao = charitiesDao;
    }

    /**
     * <p>The <code>showCharityProfile</code> method takes a charity ID and queries the database
     * for the charity. A charity object is added to populate the charity information. A
     * donation object is created at this point.</p>
     *
     * @param id integer representing the charity ID and passed as a path variable
     * @param model used to attach various objects for later reference
     * @return charity profile page
     */
    @GetMapping ("/charities/{id}")
    public String showCharityProfile (@PathVariable int id, Model model) {

        //Adds logged in user object to model for navbar personalization
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

    /**
     *<p>Shows the Thank You page after a successful donation.</p>
     *
     * @param model used to attach user object for navbar personalization
     * @return Thank you page
     */
    @GetMapping ("/thank-you")
    public String showThankYouPage (Model model) {

        //Adds logged in user object to model for navbar personalization
        if (!SecurityContextHolder.getContext().getAuthentication().getPrincipal()
                .equals("anonymousUser")) {

            User user = (User) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();

            model.addAttribute("user", user);
        }

        return "charities/thank-you";
    }
}
