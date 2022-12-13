package com.example.wishlist.enums;

public enum WishStatus {
    Taken ("Желание забронировано"),
    Free ("Желание свободно");

    private final String displayValue;

    private WishStatus(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
