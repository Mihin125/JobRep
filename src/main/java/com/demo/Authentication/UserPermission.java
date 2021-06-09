package com.demo.Authentication;

public enum UserPermission {
    USER_READ("user:read"),
    USER_WRITE("user:write"),
    OFFER_READ("offer:read"),
    OFFER_WRITE("offer:write");

    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
