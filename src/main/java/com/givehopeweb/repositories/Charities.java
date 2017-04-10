package com.givehopeweb.repositories;

import com.givehopeweb.models.Charity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * <p>The <code>Charities</code> interface provides other classes with methods to query the
 * charities table in the database. Spring Hibernate naming conventions are used for the methods
 * to auto-generate the SQL commands.</p>
 *
 * @author David Ryan Alviola
 * @since March 2017
 */
public interface Charities extends CrudRepository <Charity, Integer> {

    /**
     * <p>This method is used to search by keyword. The keyword is queried against all relevant
     * columns of the charities table.</p>
     *
     * @param name String value representing the name of a charity
     * @param category String value representing charity category
     * @param state String value representing the charity's state
     * @param city String value representing the charity's city
     * @param keyword String value used to find match via charity description
     * @return list of <code>Charity</code> objects matching the query terms
     */
    List<Charity>
        findByCharityNameContainingOrCategoryContainingOrStateContainingOrCityContainingOrDescriptionContaining
            (String name, String category, String state, String city, String keyword);

    /**
     * <p>This method is used to search by category.</p>
     *
     * @param category String value representing charity category
     * @return list of <code>Charity</code> objects matching the category
     */
    List<Charity> findByCategoryContaining (String category);

    /**
     * <p>This method is to search for a charity by its EIN.</p>
     *
     * @param ein String value representing the charity EIN
     * @return <code>Charity</code> object with matching EIN
     */
    Charity findByEin (String ein);
}