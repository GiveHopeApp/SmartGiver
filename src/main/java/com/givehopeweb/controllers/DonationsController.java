package com.givehopeweb.controllers;

import com.givehopeweb.models.Charity;
import com.givehopeweb.models.Donation;
import com.givehopeweb.repositories.Charities;
import com.givehopeweb.repositories.Donations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Created by David on 2/28/17.
 */
@Controller
public class DonationsController {

    private Charities charitiesDao;
    private Donations donationsDao;

    @Autowired
    public DonationsController (Charities charitiesDao, Donations donationsDao) {

        this.charitiesDao = charitiesDao;
        this.donationsDao = donationsDao;
    }

    @PostMapping("/donate/{token}")
    public String showConfirmationPage (@PathVariable String token,
                                        @ModelAttribute Donation donation) {

        donationsDao.save(donation);



        return "/";
    }

    @PostMapping ("/donate/{ein}")
    public String showDonationPage (@PathVariable String ein, @ModelAttribute Donation donation) {

        donation.setCharity( (Charity) charitiesDao.findByEin(ein));

        return "/charities/donation-form";
    }
}
