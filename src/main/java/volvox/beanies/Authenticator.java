package volvox.beanies;

import com.google.common.collect.Lists;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import volvox.beans.User;

import java.util.Collection;

/**
 * Wrapper around User to satisfy the security interface and leave room for secure password storage elsewhere.
 */
public class Authenticator implements UserDetails {
    private final User user;

    public Authenticator(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //TODO create admin role
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("USER");
        return Lists.newArrayList(authority);
    }

    @Override
    public String getUsername() {
        return user.getName();
    }

    @Override
    public String getPassword() { return user.getPassword(); }

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
