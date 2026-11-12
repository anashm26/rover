package com.bnp.kata.rover.model.enums;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Direction implements CodeEnum{

    NORTH('N', 'W', 'E', 0, 1),
    SOUTH('S', 'E', 'W', 0, -1),
    EAST('E', 'N', 'S', 1, 0),
    WEST('W', 'S', 'N', -1, 0);

    private final char code;
    private final char leftTurn;
    private final char rightTurn;
    private final int deltaX;
    private final int deltaY;

    public static Direction of(char code) {

        return CodeEnum.of(Direction.class, code);
    }
}
