package com.givehopeweb.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;

/**
 * <p>The <code>UserWithRole</code> class is used to implement Spring Security features for
 * authentication and authorization. The <code>UserWithRole</code> class works with the
 * <code>UserRole</code> class to authenticate and determine the level of authorization for each
 * user.</p>
 *
 * @author David Ryan Alviola
 * @since March 2017
 */
public class UserWithRole extends User implements UserDetails {

    private List<String> userRoles;

    public UserWithRole(User user, List<String> userRoles) {
        super (user);
        this.userRoles = userRoles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String roles = StringUtils.collectionToCommaDelimitedString(userRoles);
        return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
