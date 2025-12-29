package com.savina.ticketsystem.model;

public enum TicketType {

    ONE_WAY(0.4f),
    MIN_90(0.65f),
    DAILY(1.2f),
    THREE_DAY(12.00f),
    WEEKLY(20.00f),
    TWO_WEEKS(35.00f),
    MONTHLY(50.00f),
    ANNUAL(500.00f),
    LINE(1.00f),
    STUDENT(15.00f);

    private final float price;

    TicketType(float price) {
        this.price = price;
    }

    public float getPrice() {
        return price;
    }
}
