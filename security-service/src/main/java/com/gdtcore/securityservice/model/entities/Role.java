package com.gdtcore.securityservice.model.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

import static com.gdtcore.securityservice.model.entities.Permission.*;

@RequiredArgsConstructor
public enum Role {
    ROLE_USER(Set.of(
            USER_CREATE,
            USER_READ,
            USER_UPDATE,
            USER_DELETE
    )),
    ROLE_ADMIN(Set.of(
            ADMIN_CREATE,
            ADMIN_READ,
            ADMIN_UPDATE,
            ADMIN_DELETE
            )),
    ROLE_VENDOR(Set.of(
            VENDOR_CREATE,
            VENDOR_READ,
            VENDOR_UPDATE,
            VENDOR_DELETE
    )),
    ROLE_GUEST(Set.of(
            GUEST_CREATE,
            GUEST_READ,
            GUEST_UPDATE,
            GUEST_DELETE
    ));

    @Getter
    private final Set<Permission> permissions;
}
