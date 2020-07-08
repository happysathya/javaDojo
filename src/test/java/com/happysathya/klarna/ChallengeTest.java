package com.happysathya.klarna;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChallengeTest {

    @Test
    public void shouldHandleSingleDigits() {
        assertEquals("0", Challenge.numberToOrdinal(0));
        assertEquals("1st", Challenge.numberToOrdinal(1));
        assertEquals("2nd", Challenge.numberToOrdinal(2));
        assertEquals("3rd", Challenge.numberToOrdinal(3));
        assertEquals("4th", Challenge.numberToOrdinal(4));
        assertEquals("21st", Challenge.numberToOrdinal(21));
        assertEquals("42nd", Challenge.numberToOrdinal(42));
        assertEquals("111th", Challenge.numberToOrdinal(111));
        assertEquals("112th", Challenge.numberToOrdinal(112));
        assertEquals("113th", Challenge.numberToOrdinal(113));
        assertEquals("114th", Challenge.numberToOrdinal(114));
        assertEquals("11103rd", Challenge.numberToOrdinal(11103));
        assertEquals("11113th", Challenge.numberToOrdinal(11113));
    }
}