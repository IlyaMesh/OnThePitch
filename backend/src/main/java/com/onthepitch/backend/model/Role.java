package com.onthepitch.backend.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * Enum with user roles
 */
public enum Role
        implements GrantedAuthority {
    USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
