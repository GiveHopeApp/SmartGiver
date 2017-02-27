package com.givehopeweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by David on 2/22/17.
 */
@Controller
public class GiveHopeController {

    @GetMapping ("/")
    public String showLandingPage () {
        return "/home_screen";
    }

}
