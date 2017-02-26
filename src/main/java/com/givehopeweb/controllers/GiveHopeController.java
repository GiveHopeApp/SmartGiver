package com.givehopeweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by David on 2/22/17.
 */
@Controller
public class GiveHopeController {

    @GetMapping ("/")
    public String showLandingPage () {
        return "/home_screen";
    }

    @GetMapping ("/donation/payment")
    @ResponseBody
    public String makePayment () {

        String command = "curl -X POST --data " +
                "source=3QeOMPRvQquFTSAxQ6xtdiLH4ne" +
                "&amount=5000" +
                "&destination=74-2325098" +
                "&receipt_email=rubenv6776%40gmail.com" +
                "&currency=usd " +
                "https://sk_test_uJdtg4cR-W1pQl0O2dfaaw:@api.pandapay.io/v1/donations";

        StringBuffer output = new StringBuffer();

        Process process;

        try {
            process = Runtime.getRuntime().exec(command);

            process.waitFor();

            BufferedReader reader =
                    new BufferedReader (new InputStreamReader(process.getInputStream()));

            String line = "";

            while ((line = reader.readLine()) != null) {

                output.append(line + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return output.toString();
    }

}
