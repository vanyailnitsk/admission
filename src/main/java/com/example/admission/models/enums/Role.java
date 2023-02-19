package com.example.admission.models.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_GUEST,
    ROLE_USER,
    ROLE_ENTRANT,
    ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
