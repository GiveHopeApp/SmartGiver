package com.givehopeweb.controllers;

import com.givehopeweb.models.Charity;
import com.givehopeweb.models.User;
import com.givehopeweb.repositories.Charities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by David on 2/26/17.
 */
@Controller
public class SearchController {

    private Charities charitiesDao;

    @Autowired
    SearchController (Charities charitiesDao) {
        this.charitiesDao = charitiesDao;
    }

    @GetMapping("/search")
    public String showSearchPage (Model model) {

        if (!SecurityContextHolder.getContext().getAuthentication().getPrincipal()
                .equals("anonymousUser")) {

            User user = (User) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();

            model.addAttribute("user", user);
        }

        return "/charities/search";
    }

    @GetMapping ("/{searchTerm}.json")
    public @ResponseBody
    List<Charity> showSearchResults (@PathVariable String searchTerm) {

        return charitiesDao.findByCharityNameContainingOrCategoryContainingOrStateContainingOrCityContainingOrDescriptionContaining(
                searchTerm,
                searchTerm,
                searchTerm,
                searchTerm,
                searchTerm);
    }

    @GetMapping ("/category/{searchTerm}.json")
    public @ResponseBody List<Charity> showCategoryResults (@PathVariable String searchTerm) {

        return charitiesDao.findByCategoryContaining(searchTerm);
    }
}
