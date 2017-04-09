package com.givehopeweb.repositories;

import com.givehopeweb.models.User;
import org.springframework.data.repository.CrudRepository;

/**
 * <p>The <code>Users</code> interface provides other classes with methods to query the
 * users table in the database. Spring Hibernate naming conventions are used for the methods
 * to auto-generate the SQL commands.</p>
 *
 * @author David Ryan Alviola
 * @since March 2017
 */
public interface Users extends CrudRepository<User, Integer>{

    /**
     * <p>Searches the database for a user by username</p>
     *
     * @param username String value representing the username
     * @return <code>User</code> object with matching username
     */
    User findByUsername (String username);

}
