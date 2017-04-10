package com.givehopeweb.services;

import com.givehopeweb.models.User;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * <p>The <code>UserWithRole</code> class is a service used to implement Spring Security features
 * for authentication and authorization.</p>
 *
 * @author David Ryan Alviola
 * @since March 2017
 */
@Service("userService")
public class UserService {

    public boolean isLoggedIn () {
        boolean isAnonymousUser = SecurityContextHolder.getContext().getAuthentication()
                instanceof AnonymousAuthenticationToken;

        return !isAnonymousUser;
    }

    public User loggedInUser () {
        if (!isLoggedIn()) {
            return null;
        }

        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
