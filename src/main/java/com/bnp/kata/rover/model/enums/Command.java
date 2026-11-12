package com.bnp.kata.rover.model.enums;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Command implements CodeEnum{

    TURN_LEFT('L'),
    TURN_RIGHT('R'),
    MOVE_FORWARD('M');

    private final char code;

    public static Command of(char code) {

        return CodeEnum.of(Command.class, code);
    }

}
