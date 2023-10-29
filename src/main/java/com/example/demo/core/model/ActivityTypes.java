package com.example.demo.core.model;

public enum ActivityTypes {
    EDUCATION("education"),
    RECREATIONAL("recreational"),
    SOCIAL("social"),
    DIY("diy"),
    CHARITY("charity"),
    COOKING("cooking"),
    RELAXATION("relaxation"),
    MUSIC("music"),
    BUSYWORK("busywork");

    final String title;

    ActivityTypes(String title) {
        this.title = title;
    }

    public static boolean isPresent(String type) {
        try {
            Enum.valueOf(ActivityTypes.class, type);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
