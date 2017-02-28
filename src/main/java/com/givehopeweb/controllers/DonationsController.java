package com.givehopeweb.controllers;

import com.givehopeweb.models.Charity;
import com.givehopeweb.models.Donation;
import com.givehopeweb.repositories.Charities;
import com.givehopeweb.repositories.Donations;
import com.givehopeweb.services.ApiKeyLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
                                        @ModelAttribute Donation donation,
                                        @RequestParam ("email") String email) {

        donationsDao.save(donation);

        String command = "curl -X POST --data " +
                "source=" + token +
                "&amount" + donation.getAmount() +
                "&destination" + donation.getCharity().getEin() +
                "receipt_email=" + email +
                "&currency=usd" +
                "https://" + ApiKeyLoader.getPandaPayKey() + ":@api.pandapay.io/v1/donations";




        return "/";
    }

    @PostMapping ("/donate/{ein}")
    public String showDonationPage (@PathVariable String ein, @ModelAttribute Donation donation) {

        donation.setCharity( (Charity) charitiesDao.findByEin(ein));

        return "/charities/donation-form";
    }
}
