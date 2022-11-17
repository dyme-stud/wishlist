package com.example.wishlist.enums;

public enum WishPriority {
    High("Очень хочу!!!"),
    Normal("Было бы неплохо получить :)"),
    Minor("Могу обойтись");

    private final String displayValue;

    private WishPriority(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
