package com.example.wishlist.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    User, Admin;

    @Override
    public String getAuthority() {
        return name();
    }
}
