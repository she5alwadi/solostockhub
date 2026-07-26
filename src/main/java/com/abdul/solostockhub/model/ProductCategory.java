package com.abdul.solostockhub.model;

public enum ProductCategory {
    CLOTHING("Clothing"),
    FOOTWEAR("Footwear"),
    ACCESSORIES("Accessories"),
    ELECTRONICS("Electronics"),
    HOME_GOODS("Home Goods"),
    SPORTS("Sports");

    private final String displayName;

    ProductCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}