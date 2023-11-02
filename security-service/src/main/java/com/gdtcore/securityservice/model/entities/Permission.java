package com.gdtcore.securityservice.model.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    USER_CREATE("user:create"),
    USER_READ("user:read"),
    USER_UPDATE("user:update"),
    USER_DELETE("user:delete"),

    ADMIN_CREATE("admin:create"),
    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_DELETE("admin:delete"),

    VENDOR_CREATE("vendor:create"),
    VENDOR_READ("vendor:read"),
    VENDOR_UPDATE("vendor:update"),
    VENDOR_DELETE("vendor:delete"),
    GUEST_CREATE("guest:create"),
    GUEST_READ("guest:read"),
    GUEST_UPDATE("guest:update"),
    GUEST_DELETE("guest:delete");

    @Getter
    private final String permission;
    }
