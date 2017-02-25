package com.givehopeweb.controllers;

import com.givehopeweb.models.Charity;
import com.givehopeweb.repositories.Charities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by David on 2/22/17.
 */
@Controller
public class GiveHopeController {

    private Charities charitiesDao;

    @Autowired
    GiveHopeController (Charities charitiesDao) {
        this.charitiesDao = charitiesDao;
    }

    @GetMapping ("/")
    public String showLandingPage () {
        return "/home_screen";
    }

    @GetMapping ("/search")
    public String showSearchPage () {

        return "/charities/search";
    }

    @GetMapping ("/{searchTerm}.json")
    public @ResponseBody List<Charity> showSearchResults (@PathVariable String searchTerm) {

        return charitiesDao.findByCharityNameContainingOrCategoryContainingOrStateContainingOrCityContainingOrDescriptionContaining(
                searchTerm,
                searchTerm,
                searchTerm,
                searchTerm,
                searchTerm
        );
    }
}
