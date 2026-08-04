package com.abdul.solostockhub.model;

public enum Role {

    ADMIN("Administrator"),
    STAFF("Warehouse Staff"),
    CUSTOMER("Customer");

    private final String displayName;

    Role(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}