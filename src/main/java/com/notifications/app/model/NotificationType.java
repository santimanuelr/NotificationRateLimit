package com.notifications.app.model;

public enum NotificationType {

    NEWS(VALUES.NEWS),
    STATUS(VALUES.STATUS),
    MARKETING(VALUES.MARKETING);

    private String value;

    NotificationType(String value) {
        this.value = value;
    }

    public static class VALUES {
        public static final String NEWS = "NEWS";
        public static final String STATUS = "STATUS";
        public static final String MARKETING = "MARKETING";
    }
}
