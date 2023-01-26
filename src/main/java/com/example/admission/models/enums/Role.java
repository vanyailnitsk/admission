package com.example.admission.models.enums;

public enum Role /*implements GrantedAuthority*/{
    ROLE_GUEST,
    ROLE_USER,
    ROLE_ADMIN;

    public String getAuthority() {
        return name();
    }
}
