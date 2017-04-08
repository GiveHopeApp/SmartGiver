package com.givehopeweb.controllers;

import com.givehopeweb.models.Donation;
import com.givehopeweb.models.User;
import com.givehopeweb.repositories.Charities;
import com.givehopeweb.repositories.Donations;
import com.givehopeweb.services.ApiKeyLoader;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

/**
 * <p>The <code>DonationsController</code> class handles a majority of the navigation to
 * donation views.</p>
 *
 * @author David Ryan Alviola
 * @since March 2017
 */
@Controller
public class DonationsController {

    //Injected classes
    private Charities charitiesDao;
    private Donations donationsDao;
    private ApiKeyLoader apiKeyLoader;

    /**
     * <p>Constructor for the <code>DonationsController</code> class. The
     * <code>charitiesDao</code>, <code>donationsDao</code>, <code>apiKeyLoader</code> objects
     * are injected to provide methods to the class.</p>
     *
     * @param charitiesDao a <code>Charities</code> interface with methods to query the database
     * @param donationsDao a <code>Donations</code> interface with methods to query the database
     * @param apiKeyLoader a service which loads the PandaPay API key to the class
     */
    @Autowired
    public DonationsController (Charities charitiesDao, Donations donationsDao, ApiKeyLoader apiKeyLoader) {

        this.charitiesDao = charitiesDao;
        this.donationsDao = donationsDao;
        this.apiKeyLoader = apiKeyLoader;
    }

    /**
     * <p>The <code>showConfirmationPage</code> method builds the linux command to send the
     * donation to the PandaPay API.</p>
     *
     * @param token a string value representing the payment token needed by the PandaPay API
     * @param donation object containing the information for the donation
     * @param email string value pulled from donation form that represents the user email to send
     *              the receipt
     * @param model holds various objects for later reference
     * @return Redirects to thank you page
     */
    @PostMapping("/donate/confirm/{token}")
    public String showConfirmationPage (@PathVariable String token,
                                        @ModelAttribute Donation donation,
                                        @RequestParam (name = "email") String email,
                                        Model model) {

        //Adds logged in user object to model for navbar personalization
        if (!SecurityContextHolder.getContext().getAuthentication().getPrincipal()
                .equals("anonymousUser")) {

            User user = (User) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();

            model.addAttribute("user", user);
        }

        //The linux command to send donation payment to the PandaPay API. The apiKeyLoader
        // service is called and inserts the secret key. The decimal for the donation amount is
        // moved two places to the right since the PandaPay API takes the amount in cents.
        String command = "curl -X POST --data " +
                "source=" + token +
                "&amount=" + donation.getAmount().movePointRight(2) +
                "&destination=" + donation.getCharity().getEin() +
                "&receipt_email=" + email +
                "&currency=usd" +
                " https://" + apiKeyLoader.getPandaPayKey() + ":@api.pandapay.io/v1/donations";

        Process process;

        //The process executes the command and then waits for completion. The output shows the
        // response from the PandaPay API in the Spring console. Successful donations return a
        // JSON with the donation information, otherwise an error message is returned.
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

    /**
     * <p>The <code>showDonationPage</code> method shows the donation form view to the user.
     * This method uses the EIN in the path to retrieve a charity object from the database and
     * attach it to the donation object. The donation is saved to the database at this point to
     * avoid null pointer exceptions if saved after successful donation.</p>
     *
     * @param donation object containing donation information
     * @param ein path variable used to direct the donation to the specific charity
     * @param amount double value representing the amount donated
     * @param model holds various objects for later reference
     * @return Charity donation page
     */
    @PostMapping ("/donate/{ein}")
    public String showDonationPage (@ModelAttribute Donation donation, @PathVariable String ein,
                                    @RequestParam BigDecimal amount, Model model) {

        //Adds logged in user object to model for navbar personalization. If a user is logged in,
        // the user column for the donation is set to the logged in user.
        if (!SecurityContextHolder.getContext().getAuthentication().getPrincipal()
                .equals("anonymousUser")) {

            User user = (User) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();

            model.addAttribute("user", user);

            donation.setUser(user);
        }


        donation.setCharity(charitiesDao.findByEin(ein));
        donation.setAmount(amount);

        donationsDao.save(donation);

        return "/charities/donation-form";
    }

}
