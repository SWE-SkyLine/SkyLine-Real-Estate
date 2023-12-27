package com.example.SkyLine.enums;

public enum RatingEnum {
    ONE((byte) 1),
    TWO((byte) 2),
    THREE((byte) 3),
    FOUR((byte) 4),
    FIVE((byte) 5);

    private final byte value;

    RatingEnum(byte value) {
        this.value = value;
    }

    public byte getValue() {
        return value;
    }
}
