package com.givehopeweb.services;

import com.givehopeweb.models.User;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Created by David on 2/22/17.
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
