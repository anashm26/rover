package com.bnp.kata.rover.validation;

import com.bnp.kata.rover.exceptions.InvalidInstructionsFormatException;
import com.bnp.kata.rover.exceptions.RoverException;
import com.bnp.kata.rover.exceptions.RoverOutOfMarsPlateauException;
import com.bnp.kata.rover.model.MarsPlateau;
import com.bnp.kata.rover.model.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoverCoordinatesMissionValidatorTest {

    private final RoverMissionValidator roverMissionValidator = new RoverMissionValidator();

    @Test
    void validRoverPositions(){

        //given
        Position position = new Position(1, 1);
        MarsPlateau marsPlateau = new MarsPlateau(new Position(5, 5));

        //when-then
        assertDoesNotThrow(() -> roverMissionValidator.validatePosition(position, marsPlateau));
    }

    @Test
    void validInstructions(){

        //given
        String instructions = "LMRMRLMMRMLLM";

        //when-then
        assertDoesNotThrow(() -> roverMissionValidator.validateInstructions(instructions));
    }

    @Test
    void shouldThrowExceptionWhenPositionOutPlateau(){

        //given
        Position position = new Position(6, 1);
        MarsPlateau marsPlateau = new MarsPlateau(new Position(5, 5));

        //when-then
        RoverException exception = assertThrows(RoverOutOfMarsPlateauException.class,
                () -> roverMissionValidator.validatePosition(position, marsPlateau));

        String expectedErrorMessage = "Rover position (6,1) outside mars plateau (5,5).";
        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenInstructionsAreEmptyOrNull(){

        //given
        String blankInstructions = "   ";

        //when-then
        RoverException blankInstructionsException = assertThrows(InvalidInstructionsFormatException.class,
                () -> roverMissionValidator.validateInstructions(blankInstructions));


        String expectedBlankInstructionsErrorMessage = "Instructions cannot be empty";
        assertEquals(expectedBlankInstructionsErrorMessage, blankInstructionsException.getMessage());

        //when-then
        RoverException nullInstructionsException = assertThrows(InvalidInstructionsFormatException.class,
                () -> roverMissionValidator.validateInstructions(null));


        String expectedNullInstructionsExceptionErrorMessage = "Instructions cannot be empty";
        assertEquals(expectedNullInstructionsExceptionErrorMessage, nullInstructionsException.getMessage());
    }


    @Test
    void shouldThrowExceptionWhenInstructionsAreNotValidCommands(){

        //given
        String invalidInstructions = "LMRXTRM";

        //when-then
        RoverException blankInstructionsException = assertThrows(InvalidInstructionsFormatException.class,
                () -> roverMissionValidator.validateInstructions(invalidInstructions));


        String invalidInstructionsErrorMessage = "Invalid input format unknown code [X] for enum Command. Allowed values [L, R, M]";
        assertEquals(invalidInstructionsErrorMessage, blankInstructionsException.getMessage());
    }
}
