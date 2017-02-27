package com.givehopeweb.controllers;

import com.givehopeweb.models.Charity;
import com.givehopeweb.repositories.Charities;
import org.springframework.beans.factory.annotation.Autowired;
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

        Charity charity = charitiesDao.findOne(id);

        model.addAttribute("charity", charity);

        return "charities/profile";
    }
}
