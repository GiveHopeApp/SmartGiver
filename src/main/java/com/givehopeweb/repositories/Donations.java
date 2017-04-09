package com.givehopeweb.repositories;

import com.givehopeweb.models.Donation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * <p>The <code>Donations</code> interface provides other classes with methods to query the
 * donations table in the database. Spring Hibernate naming conventions are used for the methods
 * to auto-generate the SQL commands.</p>
 *
 * @author David Ryan Alviola
 * @since March 2017
 */
public interface Donations extends CrudRepository <Donation, Integer> {

    /**
     * <p>Searches the database for donations belonging to the same user.</p>
     *
     * @param userId integer value representing the user ID
     * @return list of <code>Donation</code> objects that belong to the same user
     */
    List<Donation> findByUserId (int userId);

}
