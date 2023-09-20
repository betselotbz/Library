package com.example.book.security;

import com.example.book.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


public class MyUserDetails implements UserDetails {
    public MyUserDetails(User user) {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
    // Returns the user's password.
    @Override
    public String getPassword() {
        return null;
    }
    // Returns the user's username
    @Override
    public String getUsername() {
        return null;
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
