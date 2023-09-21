package com.example.book.security;

import com.example.book.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;


public class MyUserDetails implements UserDetails {
    private final User user;

    public MyUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new HashSet<>();
    }
    // Returns the user's password.
    @Override
    public String getPassword() {
        return user.getPassword();
    }
    // Returns the user's username
    @Override
    public String getUsername() {
        return user.getEmailAddress();
    }
    //Shows if user's account has expired
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }
    //Shows if user's account is locked
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }
    //Shows if user's credential's have expired (like password)
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }
    //Shows if user account is active
    @Override
    public boolean isEnabled() {
        return false;
    }
}
