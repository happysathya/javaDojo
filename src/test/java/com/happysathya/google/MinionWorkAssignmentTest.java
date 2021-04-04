package com.happysathya.google;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class MinionWorkAssignmentTest {

    @Test
    void solution() {
        MinionWorkAssignment minionWorkAssignment = new MinionWorkAssignment();
        assertArrayEquals(new int[]{}, minionWorkAssignment.solution(new int[]{1, 2, 3}, 0));
        assertArrayEquals(new int[]{1, 4}, minionWorkAssignment.solution(new int[]{1, 2, 2, 3, 3, 3, 4, 5, 5}, 1));
        assertArrayEquals(new int[]{5, 15, 7}, minionWorkAssignment.solution(new int[]{5, 10, 15, 10, 7}, 1));
        assertArrayEquals(new int[]{2, 2}, minionWorkAssignment.solution(new int[]{1, 1, 2, 1, 2}, 2));
    }
}