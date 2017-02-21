package com.givehopeweb.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by rubenvarela on 2/20/17.
 */
@Controller
public class testController {
    @GetMapping("/home")
    public String home() {
        return "home_screen";
    }
}
