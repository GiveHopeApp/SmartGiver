package com.givehopeweb.controllers;

import com.givehopeweb.models.Donation;
import com.givehopeweb.repositories.Charities;
import com.givehopeweb.repositories.Donations;
import com.givehopeweb.services.ApiKeyLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

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

    @PostMapping("/donate/confirm/{token}")
    public String showConfirmationPage (@PathVariable String token,
                                        @ModelAttribute Donation donation,
                                        @RequestParam (name = "email") String email) {

        String command = "curl -X POST --data " +
                "source=" + token +
                "&amount=" + donation.getAmount().movePointRight(2) +
                "&destination=" + donation.getCharity().getEin() +
                "&receipt_email=" + email +
                "&currency=usd" +
                " https://" + ApiKeyLoader.getPandaPayKey() + ":@api.pandapay.io/v1/donations";

        Process process;

        try {
            process = Runtime.getRuntime().exec(command);
            process.waitFor();

            StringBuffer output = new StringBuffer();

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line = "";
            while ((line = reader.readLine())!= null) {
                output.append(line + "\n");
            }

            System.out.println(output);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return "redirect:/thank-you";
    }

    @PostMapping ("/donate/{ein}")
    public String showDonationPage (@ModelAttribute Donation donation, @PathVariable String ein,
                                    @RequestParam BigDecimal amount) {

        donation.setCharity(charitiesDao.findByEin(ein));
        donation.setAmount(amount);

        donationsDao.save(donation);

        return "/charities/donation-form";
    }

}
