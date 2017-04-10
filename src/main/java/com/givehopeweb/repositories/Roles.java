package com.givehopeweb.repositories;

import com.givehopeweb.models.UserRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>The <code>Roles</code> interface is used to implement Spring Security features for
 * authentication and authorization of users.</p>
 *
 * @author David Ryan Alviola
 * @since March 2017
 */

@Repository
public interface Roles extends CrudRepository <UserRole, Integer> {

    @Query("select ur.role from UserRole ur, User u where u.username=?1 and ur.userId = u.id")
    List<String> ofUserWith (String username);
}