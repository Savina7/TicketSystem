package com.savina.ticketsystem.model;

public enum TicketType {

    ONE_WAY(0.4f, 60),          // 1 orë
    MIN_90(0.65f, 90),          // 90 minuta
    DAILY(1.2f, 1440),          // 24 orë
    THREE_DAY(12.00f, 4320),    // 3 ditë
    WEEKLY(20.00f, 10080),      // 7 ditë
    TWO_WEEKS(35.00f, 20160),   // 14 ditë
    MONTHLY(50.00f, 43200),     // 30 ditë
    ANNUAL(500.00f, 525600),    // 365 ditë
    LINE(1.00f, 43200),         // 1 muaj (siç kërkove)
    STUDENT(15.00f, 43200);     // 1 muaj

    private final float price;
    private final int durationInMinutes;

    TicketType(float price, int durationInMinutes) {
        this.price = price;
        this.durationInMinutes = durationInMinutes;
    }

    public float getPrice() { return price; }

    public int getDurationInMinutes() { return durationInMinutes; }
}