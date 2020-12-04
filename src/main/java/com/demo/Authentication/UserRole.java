package com.demo.Authentication;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.demo.Authentication.UserPermission.*;

public enum UserRole {
    ADMIN(Sets.newHashSet(OFFER_READ,OFFER_WRITE,USER_READ,USER_WRITE)),
    INTERMEDIATE(Sets.newHashSet()),
    USER(Sets.newHashSet());

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    private final Set<UserPermission> permissions;

    public Set<UserPermission> getPermissions() {
        return permissions;
    }
}
