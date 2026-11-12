package com.bnp.kata.rover.validation;

import com.bnp.kata.rover.exceptions.InvalidInputFormatException;
import com.bnp.kata.rover.exceptions.RoverException;
import com.bnp.kata.rover.exceptions.RoverOutOfMarsPlateauException;
import com.bnp.kata.rover.model.MarsPlateau;
import com.bnp.kata.rover.model.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoverValidatorTest {

    private final RoverValidator roverValidator = new RoverValidator();

    @Test
    void validRoverPositions(){

        //given
        Position initialPosition = new Position(1, 1);
        Position currentPosition = new Position(5, 5);
        MarsPlateau marsPlateau = new MarsPlateau(new Position(5, 5));

        //when-then
        assertDoesNotThrow(() -> roverValidator.validateInitialPosition(initialPosition, marsPlateau));
        assertDoesNotThrow(() -> roverValidator.validateInitialPosition(currentPosition, marsPlateau));
    }

    @Test
    void validInstructions(){

        //given
        String instructions = "LMRMRLMMRMLLM";

        //when-then
        assertDoesNotThrow(() -> roverValidator.validateInstructions(instructions));
    }

    @Test
    void shouldThrowExceptionWhenInitialPositionOutPlateau(){

        //given
        Position initialPosition = new Position(6, 1);
        MarsPlateau marsPlateau = new MarsPlateau(new Position(5, 5));

        //when-then
        RoverException exception = assertThrows(RoverOutOfMarsPlateauException.class,
                () -> roverValidator.validateInitialPosition(initialPosition, marsPlateau));

        String expectedErrorMessage = "Initial position (6,1) outside mars plateau (5,5).";
        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCurrentPositionOutPlateau(){

        //given
        Position initialPosition = new Position(6, 1);
        MarsPlateau marsPlateau = new MarsPlateau(new Position(5, 5));

        RoverException exception = assertThrows(RoverOutOfMarsPlateauException.class,
                () -> roverValidator.validateMovement(initialPosition, marsPlateau));


        //when-then
        String expectedErrorMessage = "Rover position (6,1) outside mars plateau (5,5).";
        assertEquals(expectedErrorMessage, exception.getMessage());
    }


    @Test
    void shouldThrowExceptionWhenInstructionsAreEmptyOrNull(){

        //given
        String blankInstructions = "   ";

        //when-then
        RoverException blankInstructionsException = assertThrows(InvalidInputFormatException.class,
                () -> roverValidator.validateInstructions(blankInstructions));


        String expectedBlankInstructionsErrorMessage = "Instructions cannot be empty";
        assertEquals(expectedBlankInstructionsErrorMessage, blankInstructionsException.getMessage());

        //when-then
        RoverException nullInstructionsException = assertThrows(InvalidInputFormatException.class,
                () -> roverValidator.validateInstructions(null));


        String expectedNullInstructionsExceptionErrorMessage = "Instructions cannot be empty";
        assertEquals(expectedNullInstructionsExceptionErrorMessage, nullInstructionsException.getMessage());
    }


    @Test
    void shouldThrowExceptionWhenInstructionsAreNotValidCommands(){

        //given
        String invalidInstructions = "LMRXTRM";

        //when-then
        RoverException blankInstructionsException = assertThrows(InvalidInputFormatException.class,
                () -> roverValidator.validateInstructions(invalidInstructions));


        String invalidInstructionsErrorMessage = "Invalid input format unknown code [X] for enum Command. Allowed values [L, R, M]";
        assertEquals(invalidInstructionsErrorMessage, blankInstructionsException.getMessage());
    }
}
