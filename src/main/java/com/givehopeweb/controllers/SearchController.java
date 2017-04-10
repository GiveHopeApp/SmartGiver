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
 * <p>The <code>SearchController</code> class contains methods for searching the database for
 * charities based on a user search term.</p>
 *
 * @author David Ryan Alviola
 * @since March 2017
 */
@Controller
public class SearchController {

    //Injected classes
    private Charities charitiesDao;

    /**
     * <p>Constructor for the <code>SearchController</code> class. The <code>charitiesDao</code>
     * object is inserted to provide methods for searching the database.</p>
     *
     * @param charitiesDao a <code>Charities</code> interface with methods to query the database
     */
    @Autowired
    SearchController (Charities charitiesDao) {
        this.charitiesDao = charitiesDao;
    }

    /**
     * <p>The <code>showSearchResults</code> method takes a user search term as a path variable
     * and queries the database across all columns.</p>
     *
     * @param searchTerm String value representing a user search term
     * @return list of search results as a JSON object
     */
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

    /**
     * <p>The <code>showCategoryResults</code> method queries the database for charities with a
     * category that matches the search term.</p>
     *
     * @param searchTerm String value representing a user search term
     * @return list of search results as a JSON object
     */
    @GetMapping ("/category/{searchTerm}.json")
    public @ResponseBody
    List<Charity> showCategoryResults (@PathVariable String searchTerm) {

        return charitiesDao.findByCategoryContaining(searchTerm);
    }
}
