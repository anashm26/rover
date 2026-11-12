package com.bnp.kata.rover.model.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DirectionTest {

    @Test
    void leftTurnCycles() {

        assertEquals(Direction.WEST, Direction.of(Direction.NORTH.getLeftTurn()));
        assertEquals(Direction.EAST, Direction.of(Direction.SOUTH.getLeftTurn()));
        assertEquals(Direction.NORTH,Direction.of( Direction.EAST.getLeftTurn()));
        assertEquals(Direction.SOUTH,Direction.of( Direction.WEST.getLeftTurn()));
    }
    @Test
    void rightTurnCycles() {

        assertEquals(Direction.EAST, Direction.of(Direction.NORTH.getRightTurn()));
        assertEquals(Direction.WEST, Direction.of(Direction.SOUTH.getRightTurn()));
        assertEquals(Direction.SOUTH,Direction.of(Direction.EAST.getRightTurn()));
        assertEquals(Direction.NORTH,Direction.of(Direction.WEST.getRightTurn()));
    }

}
